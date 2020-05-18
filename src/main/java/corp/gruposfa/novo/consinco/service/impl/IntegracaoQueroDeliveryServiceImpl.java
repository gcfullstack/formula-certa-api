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
import corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO;
import corp.gruposfa.novo.consinco.model.dto.CategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarDescricaoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarEstoqueDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarNomeDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarPrecoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarStatusDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoCadastroQueroDeliveryDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseAddCategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseCategoriaResultDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseProdutoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseProdutoDadoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseTodosProdutosDTO;
import corp.gruposfa.novo.consinco.service.CategoriaService;
import corp.gruposfa.novo.consinco.service.IntegracaoQueroDeliveryService;
import corp.gruposfa.novo.consinco.service.LogIntegracaoQueroDeliveryService;
import corp.gruposfa.novo.consinco.service.ProdutoService;
import corp.gruposfa.novo.feign.ServicoIntegracaoFeignClient;
import corp.gruposfa.novo.model.CategoriaQueroDelivery;
import corp.gruposfa.novo.model.LogIntegracaoQueroDelivery;
import corp.gruposfa.novo.model.dto.CategoriaQueroDeliveryDTO;
import corp.gruposfa.novo.service.CategoriaQueroDeliveryService;
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
	
	private final CategoriaQueroDeliveryService categoriaQueroDeliveryService;
	
	private final static String STATUS_OCULTO = "OCULTO";
	
	private final static String STATUS_ATIVO_CONSINCO = "A";
	
	private final static String CATEGORIA_A_CLASSIFICAR = "A CLASSIFICAR";

	public IntegracaoQueroDeliveryServiceImpl(CategoriaQueroDeliveryFeignClient categoriaQueroDeliveryFeignClient,CategoriaService categoriaService,ProdutoQueroDeliveryFeignClient produtoQueroDeliveryFeignClient,ProdutoService produtoService,LogIntegracaoQueroDeliveryService logIntegracaoQueroDeliveryService,ServicoIntegracaoFeignClient servicoIntegracaoFeignClient,CategoriaQueroDeliveryService categoriaQueroDeliveryService) {
		this.categoriaQueroDeliveryFeignClient = categoriaQueroDeliveryFeignClient;
		this.categoriaService = categoriaService;
		this.produtoQueroDeliveryFeignClient = produtoQueroDeliveryFeignClient;
		this.produtoService = produtoService;
		this.logIntegracaoQueroDeliveryService = logIntegracaoQueroDeliveryService;
		this.servicoIntegracaoFeignClient = servicoIntegracaoFeignClient;
		this.categoriaQueroDeliveryService = categoriaQueroDeliveryService;
	}
	
	@Override
	public void executarJobIntegracao() {
		if(Boolean.valueOf(servicoIntegracaoFeignClient.verificarStatusExecucaoJob(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY))) {
			servicoIntegracaoFeignClient.alterarStatusExecucaoJob(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY, Boolean.FALSE);
			servicoIntegracaoFeignClient.salvarInicioDeExecucaoDeServico(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY);
			try {
				integrarDadosQueroDelivery();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		salvarCategorias();
		removerCategorias();
	}
	
	private void salvarCategorias() {
		// ADICIONAR CATEGORIAS
		List<CategoriaCompareDTO> categoriasConsinco = categoriaService.buscarCategorias();
		List<CategoriaCompareDTO> categoriasSalvas = categoriaQueroDeliveryService.buscarRegistros();
		List<CategoriaCompareDTO> categoriasParaAdicionar = new ArrayList<>();
		Boolean categoriaJaExistente = Boolean.FALSE;
		
		for (CategoriaCompareDTO catCS : categoriasConsinco) {
			for (CategoriaCompareDTO catQD : categoriasSalvas) {
				if(catCS.getCodCategoria().equals(catQD.getCodCategoria())) {
					categoriaJaExistente = Boolean.TRUE;
					break;
				}
			}
			
			if(!categoriaJaExistente) {
				categoriasParaAdicionar.add(catCS);
			}
			categoriaJaExistente = Boolean.FALSE;
		}
		
		for (CategoriaCompareDTO categoria : categoriasParaAdicionar) {
			if(!categoria.getNomeCategoria().equals(CATEGORIA_A_CLASSIFICAR)) {
				ResponseAddCategoriaDTO categoriaSalvaNoAPP = categoriaQueroDeliveryFeignClient.adicionarCategoria(new CategoriaDTO(categoria.getNomeCategoria(),Boolean.TRUE));
				categoriaQueroDeliveryService.salvarRegistro(new CategoriaQueroDelivery(categoria.getNomeCategoria(), categoria.getCodCategoria(), categoriaSalvaNoAPP.getData().get_id()));
				logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ADICIONAR_CATEGORIA,"Categoria adicionada: " + categoria.getNomeCategoria(), null, categoria.getNomeCategoria(),null));
			}
		}
	}
	
	private void removerCategorias() {
		List<CategoriaCompareDTO> categoriasConsinco =  categoriaService.buscarCategorias();
		List<CategoriaCompareDTO> categoriasSalvas = categoriaQueroDeliveryService.buscarRegistros();
		List<CategoriaCompareDTO> categoriasParaRemover = new ArrayList<>();
		Boolean categoriaExistente = Boolean.FALSE;
		
		for (CategoriaCompareDTO catSalva : categoriasSalvas) {
			for (CategoriaCompareDTO catConsinco : categoriasConsinco) {
				if(catSalva.getCodCategoria().equals(catConsinco.getCodCategoria())) {
					categoriaExistente = Boolean.TRUE;
					break;
				}
			}
			
			if(!categoriaExistente) {
				categoriasParaRemover.add(catSalva);
			}
			categoriaExistente = Boolean.FALSE;
		}
		
		List<ResponseProdutoDadoDTO> listaProdutos = categoriasParaRemover.size() > 0 ? buscarTodosProdutos() : null;
		
		// excluir as categorias que não estão mais no banco da consinco
		
		for (CategoriaCompareDTO cat : categoriasParaRemover) {
			CategoriaQueroDeliveryDTO categoriaSalva = categoriaQueroDeliveryService.buscarRegistroPorCodCategoriaConsinco(cat.getCodCategoria());
			List<ResponseProdutoDadoDTO> produtosParaExcluir = listaProdutos.stream().filter(x -> x.getProdutoCategoriaId().equals(categoriaSalva.getCodCategoriaQueroDelivery())).collect(Collectors.toList());
			for (ResponseProdutoDadoDTO p : produtosParaExcluir) {
				produtoQueroDeliveryFeignClient.excluirProdutoPorCodigoBarras(p.getCodigoBarras());
			}
			categoriaQueroDeliveryFeignClient.excluirCategoria(categoriaSalva.getCodCategoriaQueroDelivery());
			categoriaQueroDeliveryService.excluirRegistroPorCodCategoriaConsinco(cat.getCodCategoria());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.INATIVAR_CATEGORIA,"Categoria excluída: " + cat.getNomeCategoria(), null, cat.getNomeCategoria(),null));
		}
		
	}
	
	private void integrarProduto() {
		List<ProdutoDTO> listaProdutos = produtoService.buscarInformacoesIntegracaoProduto();
		for (ProdutoDTO produtoDTO : listaProdutos) {
			ResponseProdutoDTO response = produtoQueroDeliveryFeignClient.buscarProdutoPorCodBarras(produtoDTO.getCodBarras());
			if(!response.getR()) {
				CategoriaQueroDeliveryDTO categoriaEncontrada = categoriaQueroDeliveryService.buscarRegistroPorCodCategoriaConsinco(produtoDTO.getCodCategoria());
			    if(validarProdutoAntesSalvar(produtoDTO,categoriaEncontrada)) {
			    	ProdutoCadastroQueroDeliveryDTO produto = new ProdutoCadastroQueroDeliveryDTO(produtoDTO.getNomeProduto(), categoriaEncontrada.getCodCategoriaQueroDelivery(), produtoDTO.getNomeProduto(), "ATIVO", produtoDTO.getPrecoVarejo(), BigDecimal.ZERO, produtoDTO.getCodBarras(), produtoDTO.getCodProduto().toString(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
					produtoQueroDeliveryFeignClient.adicionarProduto(produto);
					logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ADICIONAR_PRODUTO,"Produto adicionado: " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null,produtoDTO.getNomeProduto()));
			    }
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
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_ESTOQUE_PRODUTO,"Estoque Atualizado. Valor antigo: " + estoqueQueroDelivery + "/ Valor novo: " + estoqueConsinco, produtoEncontrado.getCodigoBarras(), null, produtoEncontrado.getNome()));

		}
	}
	
	private void validarPrecoProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco) {
		if(produtoBaseConsinco.getPrecoVarejo().compareTo(produtoEncontrado.getPreco()) != 0) {
			produtoQueroDeliveryFeignClient.atualizarPrecoProduto(new ProdutoAtualizarPrecoDTO(produtoBaseConsinco.getPrecoVarejo(), produtoEncontrado.getPreco()), produtoEncontrado.getCodigoBarras());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_PRECO_PRODUTO,"Preço Atualizado. Valor antigo: " + produtoEncontrado.getPreco() + "/ Valor novo: " + produtoBaseConsinco.getPrecoVarejo(), produtoEncontrado.getCodigoBarras(), null, produtoEncontrado.getNome()));
		}
	}
	
	private void validarStatusProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco) {
		if(!produtoBaseConsinco.getStatusVenda().equals(STATUS_ATIVO_CONSINCO)) {
			produtoQueroDeliveryFeignClient.atualizarStatusProduto(new ProdutoAtualizarStatusDTO(STATUS_OCULTO), produtoEncontrado.getCodigoBarras());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_STATUS_PRODUTO,"Status Atualizado. Novo status: " + STATUS_OCULTO, produtoEncontrado.getCodigoBarras(), null, produtoEncontrado.getNome()));
		}
	}
	
	private void validarNomeEDescricaoProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco) {
		if(!produtoBaseConsinco.getNomeProduto().equals(produtoEncontrado.getDescricao())) {
			produtoQueroDeliveryFeignClient.atualizarDescricaoProduto(new ProdutoAtualizarDescricaoDTO(produtoBaseConsinco.getNomeProduto()), produtoEncontrado.getCodigoBarras());
			produtoQueroDeliveryFeignClient.atualizarNomeProduto(new ProdutoAtualizarNomeDTO(MethodsUtils.capitalizarString(produtoBaseConsinco.getNomeProduto())), produtoEncontrado.getCodigoBarras());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_DESCRICAO_PRODUTO,"Nome/Descrição Atualizados. Valor antigo: " + produtoEncontrado.getDescricao() + "/ Valor novo: " + produtoBaseConsinco.getNomeProduto(), produtoEncontrado.getCodigoBarras(), null,produtoBaseConsinco.getNomeProduto()));

		}
	}

	private Boolean validarProdutoAntesSalvar(ProdutoDTO produtoDTO, CategoriaQueroDeliveryDTO categoria) {
		if(!validarSeProdutoPossuiCategoria(categoria)) {
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_SEM_CATEGORIA,"Produto sem categoria encontrado: : " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null, produtoDTO.getNomeProduto()));
			return Boolean.FALSE;
		}
		if(!validarCodigoBarrasDoProduto(produtoDTO)) {
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_SEM_COD_BARRAS,"Produto sem código de barras encontrado: : " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null, produtoDTO.getNomeProduto()));
			return Boolean.FALSE;
		}
		
		if(!validarPrecoDoProduto(produtoDTO)) {
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_PRECO_ZERADO,"Produto sem preço encontrado: : " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null, produtoDTO.getNomeProduto()));
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	private Boolean validarSeProdutoPossuiCategoria(CategoriaQueroDeliveryDTO categoria) {
		return categoria != null && !categoria.getNomeCategoria().equals(CATEGORIA_A_CLASSIFICAR);
	}
	
	private Boolean validarCodigoBarrasDoProduto(ProdutoDTO produtoDTO) {
		return !produtoDTO.getCodBarras().equals("0");
	}
	
	private Boolean validarPrecoDoProduto(ProdutoDTO produtoDTO) {
		return produtoDTO.getPrecoVarejo() != null && produtoDTO.getPrecoVarejo().compareTo(BigDecimal.ZERO) > 0;
	}
	
	private List<ResponseProdutoDadoDTO> buscarTodosProdutos(){
		List<ResponseProdutoDadoDTO> list = new ArrayList<>();
		ResponseTodosProdutosDTO response = null;
		Integer offset = 0;
		while(true) {
			response = produtoQueroDeliveryFeignClient.buscarProdutos(offset, 50);
			if(response.getData() == null) {
				break;
			}
			list.addAll(response.getData().getProdutos());
			offset = offset + response.getData().getProdutos().size();
		}
		return list;
	}
	
}
