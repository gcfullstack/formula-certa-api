package corp.gruposfa.novo.consinco.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.enumeration.TipoLogIntegracaoEnum;
import corp.gruposfa.novo.consinco.feign.CategoriaQueroDeliveryFeignClient;
import corp.gruposfa.novo.consinco.feign.ProdutoQueroDeliveryFeignClient;
import corp.gruposfa.novo.consinco.model.dto.CategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.CategoriaQueroDeliveryDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarDescricaoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarEstoqueDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarNomeDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarPrecoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarStatusDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoCadastroQueroDeliveryDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseCategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseCategoriaResultDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseProdutoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseProdutoDadoDTO;
import corp.gruposfa.novo.consinco.service.CategoriaService;
import corp.gruposfa.novo.consinco.service.IntegracaoQueroDeliveryService;
import corp.gruposfa.novo.consinco.service.LogIntegracaoQueroDeliveryService;
import corp.gruposfa.novo.consinco.service.ProdutoService;
import corp.gruposfa.novo.feign.ServicoIntegracaoFeignClient;
import corp.gruposfa.novo.model.LogIntegracaoQueroDelivery;
import corp.gruposfa.novo.utils.ConstantsUtils;
import corp.gruposfa.novo.utils.MethodsUtils;

@Service
public class IntegracaoQueroDeliveryServiceImpl implements IntegracaoQueroDeliveryService{

	private final CategoriaQueroDeliveryFeignClient categoriaQueroDeliveryFeignClient;
	
	private final ProdutoQueroDeliveryFeignClient produtoQueroDeliveryFeignClient;
	
	private final ServicoIntegracaoFeignClient servicoIntegracaoFeignClient;
	
	private final CategoriaService categoriaService;
	
	private final ProdutoService produtoService;
	
	private final LogIntegracaoQueroDeliveryService logIntegracaoQueroDeliveryService;
	
	private final static String STATUS_OCULTO = "OCULTO";
	
	private final static String STATUS_ATIVO_CONSINCO = "A";

	public IntegracaoQueroDeliveryServiceImpl(CategoriaQueroDeliveryFeignClient categoriaQueroDeliveryFeignClient,CategoriaService categoriaService,ProdutoQueroDeliveryFeignClient produtoQueroDeliveryFeignClient,ProdutoService produtoService,LogIntegracaoQueroDeliveryService logIntegracaoQueroDeliveryService,ServicoIntegracaoFeignClient servicoIntegracaoFeignClient) {
		this.categoriaQueroDeliveryFeignClient = categoriaQueroDeliveryFeignClient;
		this.categoriaService = categoriaService;
		this.produtoQueroDeliveryFeignClient = produtoQueroDeliveryFeignClient;
		this.produtoService = produtoService;
		this.logIntegracaoQueroDeliveryService = logIntegracaoQueroDeliveryService;
		this.servicoIntegracaoFeignClient = servicoIntegracaoFeignClient;
	}
	
	@Override
	public void executarJobIntegracao() {
		if(!Boolean.valueOf(servicoIntegracaoFeignClient.verificarStatusExecucaoJob(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY))) {
			servicoIntegracaoFeignClient.alterarStatusExecucaoJob(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY, Boolean.FALSE);
			servicoIntegracaoFeignClient.salvarInicioDeExecucaoDeServico(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY);
			integrarDadosQueroDelivery();
			servicoIntegracaoFeignClient.salvarFimDeExecucaoDeServico(18);
			servicoIntegracaoFeignClient.alterarStatusExecucaoJob(18,Boolean.TRUE);
		}
	}
	
	@Override
	public void integrarDadosQueroDelivery() {
		integrarCategoria();
		integrarProduto();
	}
	
	private void integrarCategoria() {
		// buscar categorias cadastradas no banco da consinco
		List<String> nomesCategoriasConsinco = categoriaService.buscarNomeCategorias();
		List<String> categoriasParaAdicionar = new ArrayList<>();
		categoriasParaAdicionar.addAll(nomesCategoriasConsinco);
		
		// buscar categorias do APP Quero Delivery
		ResponseCategoriaDTO responseQueroDelivery = categoriaQueroDeliveryFeignClient.buscarCategorias();
		List<CategoriaQueroDeliveryDTO> collect = responseQueroDelivery.getData().getResult().stream().map(x -> new CategoriaQueroDeliveryDTO(x.get_id(),x.getNome(),x.getIsAtivo())).collect(Collectors.toList());
		List<String> listaQueroDelivery = collect.stream().map(x -> x.getNome()).collect(Collectors.toList());
		
		// filtrar as categorias que ainda não existem no APP Quero Delivery
		categoriasParaAdicionar.removeAll(listaQueroDelivery);
		
		// adicionar as categorias que não existem no APP Quero Delivery
		for (String nomeCategoria : categoriasParaAdicionar) {
			if(nomeCategoria != null) {
				categoriaQueroDeliveryFeignClient.adicionarCategoria(new CategoriaDTO(nomeCategoria,Boolean.TRUE));
				logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ADICIONAR_CATEGORIA,"Categoria adicionada: " + nomeCategoria, null, nomeCategoria));
			}
		}
		
		// filtrar as categorias para obter aquelas que não estão mais no banco da consinco
		listaQueroDelivery.removeAll(nomesCategoriasConsinco);
		List<CategoriaQueroDeliveryDTO> listaParaInativar = new ArrayList<>();
		for (String nomeCat : listaQueroDelivery) {
			for (CategoriaQueroDeliveryDTO c : collect) {
				if(nomeCat.equals(c.getNome())) {
					if(c.getIsAtivo()) {
						listaParaInativar.add(c);
					}
				}
			}
		}
		
		// inativar as categorias que não estão mais no banco da consinco
		for (CategoriaQueroDeliveryDTO cat : listaParaInativar) {
			categoriaQueroDeliveryFeignClient.editarCategoria(new CategoriaDTO(cat.getNome(), Boolean.FALSE), cat.getId());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.INATIVAR_CATEGORIA,"Categoria inativada: " + cat.getNome(), null, cat.getNome()));
		}
		
	}
	
	private void integrarProduto() {
		List<ResponseCategoriaResultDTO> result = categoriaQueroDeliveryFeignClient.buscarCategorias().getData().getResult();
		List<ProdutoDTO> listaProdutos = produtoService.buscarInformacoesIntegracaoProduto();
		for (ProdutoDTO produtoDTO : listaProdutos) {
			ResponseProdutoDTO response = produtoQueroDeliveryFeignClient.buscarProdutoPorCodBarras(produtoDTO.getCodBarras());
			if(!response.getR()) {
			    ResponseCategoriaResultDTO categoriaEncontrada = result.stream().filter(x -> x.getNome().equals(produtoDTO.getNomeCategoria())).findFirst().orElse(null);
				ProdutoCadastroQueroDeliveryDTO produto = new ProdutoCadastroQueroDeliveryDTO(produtoDTO.getNomeProduto(), categoriaEncontrada.get_id(), produtoDTO.getNomeProduto(), "ATIVO", produtoDTO.getPrecoVarejo(), BigDecimal.ZERO, produtoDTO.getCodBarras(), produtoDTO.getCodProduto().toString(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
				produtoQueroDeliveryFeignClient.adicionarProduto(produto);
				logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ADICIONAR_PRODUTO,"Produto adicionado: " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null));
			}else {
				validarPrecoProduto(response.getData(), produtoDTO);
				validarStatusProduto(response.getData(), produtoDTO);
				validarNomeEDescricaoProduto(response.getData(), produtoDTO);
				atualizarEstoqueProduto(response.getData(),produtoDTO);
			}
		}
	}
	
	private void atualizarEstoqueProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco) {
		Integer estoqueQueroDelivery = produtoQueroDeliveryFeignClient.buscarEstoque(produtoEncontrado.getCodigoBarras()).getData().getProduto().getQtdEstoque();
		Integer estoqueConsinco = produtoBaseConsinco.getQtdEstoqueMenorEmb();
		if(!estoqueQueroDelivery.equals(produtoBaseConsinco.getQtdEstoqueMenorEmb())) {
			produtoQueroDeliveryFeignClient.atualizarEstoqueProduto(new ProdutoAtualizarEstoqueDTO(estoqueConsinco - estoqueQueroDelivery), produtoEncontrado.getCodigoBarras());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_ESTOQUE_PRODUTO,"Estoque Atualizado. Valor antigo: " + estoqueQueroDelivery + "/ Valor novo: " + (estoqueConsinco - estoqueQueroDelivery), produtoEncontrado.getCodigoBarras(), null));

		}
	}
	
	private void validarPrecoProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco) {
		if(produtoBaseConsinco.getPrecoVarejo().compareTo(produtoEncontrado.getPreco()) != 0) {
			produtoQueroDeliveryFeignClient.atualizarPrecoProduto(new ProdutoAtualizarPrecoDTO(produtoBaseConsinco.getPrecoVarejo(), produtoEncontrado.getPreco()), produtoEncontrado.getCodigoBarras());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_PRECO_PRODUTO,"Preço Atualizado. Valor antigo: " + produtoEncontrado.getPreco() + "/ Valor novo: " + produtoBaseConsinco.getPrecoVarejo(), produtoEncontrado.getCodigoBarras(), null));
		}
	}
	
	private void validarStatusProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco) {
		if(!produtoBaseConsinco.getStatusVenda().equals(STATUS_ATIVO_CONSINCO)) {
			produtoQueroDeliveryFeignClient.atualizarStatusProduto(new ProdutoAtualizarStatusDTO(STATUS_OCULTO), produtoEncontrado.getCodigoBarras());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_STATUS_PRODUTO,"Status Atualizado. Novo status: " + STATUS_OCULTO, produtoEncontrado.getCodigoBarras(), null));
		}
	}
	
	private void validarNomeEDescricaoProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco) {
		if(!produtoBaseConsinco.getNomeProduto().equals(produtoEncontrado.getDescricao())) {
			produtoQueroDeliveryFeignClient.atualizarDescricaoProduto(new ProdutoAtualizarDescricaoDTO(produtoBaseConsinco.getNomeProduto()), produtoEncontrado.getCodigoBarras());
			produtoQueroDeliveryFeignClient.atualizarNomeProduto(new ProdutoAtualizarNomeDTO(MethodsUtils.capitalizarString(produtoBaseConsinco.getNomeProduto())), produtoEncontrado.getCodigoBarras());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_DESCRICAO_PRODUTO,"Nome/Descrição Atualizados. Valor antigo: " + produtoEncontrado.getDescricao() + "/ Valor novo: " + produtoBaseConsinco.getNomeProduto(), produtoEncontrado.getCodigoBarras(), null));

		}
	}
	
}
