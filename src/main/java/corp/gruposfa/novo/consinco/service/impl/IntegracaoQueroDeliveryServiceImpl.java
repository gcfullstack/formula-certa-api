package corp.gruposfa.novo.consinco.service.impl;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.enumeration.TipoLogIntegracaoEnum;
import corp.gruposfa.novo.consinco.feign.CategoriaQueroDeliveryFeignClient;
import corp.gruposfa.novo.consinco.feign.ProdutoQueroDeliveryFeignClient;
import corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO;
import corp.gruposfa.novo.consinco.model.dto.CategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ParametrizacaoAmbienteDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAlterarControlaEstoqueDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarDescricaoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarEstoqueDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarNomeDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarPrecoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarStatusDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoCadastroQueroDeliveryDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseAddCategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseProdutoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseProdutoDadoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseTodosProdutosDTO;
import corp.gruposfa.novo.consinco.properties.QueroDeliveryProperties;
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
	
	private final static String STATUS_ATIVO = "ATIVO";
	
	private final static String STATUS_ATIVO_CONSINCO = "A";
	
	private final static String CATEGORIA_A_CLASSIFICAR = "A CLASSIFICAR";
	
	private final static String AMBIENTE_PROD = "PROD";
	
	private final QueroDeliveryProperties queroDeliveryProperties;
	
	private final static HashMap<Integer, String> MAP_LOJAS = new HashMap<Integer, String>() {
		{
			put(1, "5f07430382dcfd002069942f");
			put(2, "5e988d39517ce60141182af6");
		}
	};
	
	public IntegracaoQueroDeliveryServiceImpl(CategoriaQueroDeliveryFeignClient categoriaQueroDeliveryFeignClient,CategoriaService categoriaService,ProdutoQueroDeliveryFeignClient produtoQueroDeliveryFeignClient,ProdutoService produtoService,LogIntegracaoQueroDeliveryService logIntegracaoQueroDeliveryService,ServicoIntegracaoFeignClient servicoIntegracaoFeignClient,CategoriaQueroDeliveryService categoriaQueroDeliveryService,QueroDeliveryProperties queroDeliveryProperties) {
		this.categoriaQueroDeliveryFeignClient = categoriaQueroDeliveryFeignClient;
		this.categoriaService = categoriaService;
		this.produtoQueroDeliveryFeignClient = produtoQueroDeliveryFeignClient;
		this.produtoService = produtoService;
		this.logIntegracaoQueroDeliveryService = logIntegracaoQueroDeliveryService;
		this.servicoIntegracaoFeignClient = servicoIntegracaoFeignClient;
		this.categoriaQueroDeliveryService = categoriaQueroDeliveryService;
		this.queroDeliveryProperties = queroDeliveryProperties;
	}
	
	@Override
	public void executarJobIntegracao() {
		if(Boolean.valueOf(servicoIntegracaoFeignClient.verificarStatusExecucaoJob(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY))) {
			servicoIntegracaoFeignClient.alterarStatusExecucaoJob(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY, Boolean.FALSE);
			servicoIntegracaoFeignClient.salvarInicioDeExecucaoDeServico(ConstantsUtils.ID_INTEGRACAO_QUERO_DELIVERY);
			try {
				for (Map.Entry<Integer, String> entry : MAP_LOJAS.entrySet()) {
				    Integer codLoja = entry.getKey();
				    String placeID = entry.getValue();
					integrarDadosQueroDelivery(new ParametrizacaoAmbienteDTO(placeID,queroDeliveryProperties.getTokenProd(),AMBIENTE_PROD, queroDeliveryProperties.getUrlApiProd(),codLoja));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			servicoIntegracaoFeignClient.salvarFimDeExecucaoDeServico(18);
			servicoIntegracaoFeignClient.alterarStatusExecucaoJob(18,Boolean.TRUE);
		}
	}
	
	@Override
	public void integrarDadosQueroDelivery(ParametrizacaoAmbienteDTO param) {
		integrarCategoria(param);
		integrarProduto(param);
	}
	
	private void integrarCategoria(ParametrizacaoAmbienteDTO param) {
		salvarCategorias(param);
		removerCategorias(param);
	}
	
	private void salvarCategorias(ParametrizacaoAmbienteDTO param) {
		// ADICIONAR CATEGORIAS
		List<CategoriaCompareDTO> categoriasConsinco = categoriaService.buscarCategorias(param.getCodLoja());
		List<CategoriaCompareDTO> categoriasSalvas = categoriaQueroDeliveryService.buscarRegistros(param.getAmbiente(),param.getCodLoja());
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
				ResponseAddCategoriaDTO categoriaSalvaNoAPP = categoriaQueroDeliveryFeignClient.adicionarCategoria(new CategoriaDTO(categoria.getNomeCategoria(),Boolean.TRUE),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
				if(categoriaSalvaNoAPP.getR()) {
					categoriaQueroDeliveryService.salvarRegistro(new CategoriaQueroDelivery(categoria.getNomeCategoria(), categoria.getCodCategoria(), categoriaSalvaNoAPP.getData().get_id(), param.getAmbiente(), param.getCodLoja()));
					logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ADICIONAR_CATEGORIA,"Categoria adicionada: " + categoria.getNomeCategoria(), null, categoria.getNomeCategoria(),null,param.getAmbiente(),param.getCodLoja()));
				}
			}
		}
	}
	
	private void removerCategorias(ParametrizacaoAmbienteDTO param) {
		List<CategoriaCompareDTO> categoriasConsinco =  categoriaService.buscarCategorias(param.getCodLoja());
		List<CategoriaCompareDTO> categoriasSalvas = categoriaQueroDeliveryService.buscarRegistros(param.getAmbiente(), param.getCodLoja());
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
		
		List<ResponseProdutoDadoDTO> listaProdutos = categoriasParaRemover.size() > 0 ? buscarTodosProdutos(param) : null;
		
		// excluir as categorias que não estão mais no banco da consinco
		
		for (CategoriaCompareDTO cat : categoriasParaRemover) {
			CategoriaQueroDeliveryDTO categoriaSalva = categoriaQueroDeliveryService.buscarRegistroPorCodCategoriaConsinco(cat.getCodCategoria(),param.getAmbiente(),param.getCodLoja());
			List<ResponseProdutoDadoDTO> produtosParaExcluir = listaProdutos.stream().filter(x -> x.getProdutoCategoriaId().equals(categoriaSalva.getCodCategoriaQueroDelivery())).collect(Collectors.toList());
			for (ResponseProdutoDadoDTO p : produtosParaExcluir) {
				produtoQueroDeliveryFeignClient.excluirProdutoPorCodigoBarras(p.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
				logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.EXCLUIR_PRODUTO,"Produto excluído: " + p.getNome(),p.getCodigoBarras(), null,p.getNome(),param.getAmbiente(), param.getCodLoja()));

			}
			categoriaQueroDeliveryFeignClient.excluirCategoria(categoriaSalva.getCodCategoriaQueroDelivery(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
			categoriaQueroDeliveryService.excluirRegistroPorCodCategoriaConsinco(cat.getCodCategoria(),param.getAmbiente(),param.getCodLoja());
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.INATIVAR_CATEGORIA,"Categoria excluída: " + cat.getNomeCategoria(), null, cat.getNomeCategoria(),null,param.getAmbiente(), param.getCodLoja()));
		}
		
	}
	
	private void integrarProduto(ParametrizacaoAmbienteDTO param) {
		removerProdutos(param);
		salvarProduto(param);
	}
	
	private void removerProdutos(ParametrizacaoAmbienteDTO param) {
		List<ProdutoDTO> listaProdutosConsinco = produtoService.buscarInformacoesIntegracaoProduto(param.getCodLoja());
		List<ResponseProdutoDadoDTO> listaProdutosQueroDelivery = buscarTodosProdutos(param);
		Boolean encontrou = Boolean.FALSE;
		for (ResponseProdutoDadoDTO prodQueroDelivery : listaProdutosQueroDelivery) {
			for (ProdutoDTO prodConsinco : listaProdutosConsinco) {
				if(prodQueroDelivery.getCodigoInterno().equals(prodConsinco.getCodProduto().toString())) {
					encontrou = Boolean.TRUE;
					break;
				}
			}
			
			if(!encontrou) {
				if(produtoQueroDeliveryFeignClient.excluirProdutoPorCodigoBarras(prodQueroDelivery.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl())).getR()) {
					logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.EXCLUIR_PRODUTO,"Produto excluído: " + prodQueroDelivery.getNome(),prodQueroDelivery.getCodigoBarras(), null,prodQueroDelivery.getNome(),param.getAmbiente(), param.getCodLoja()));
				}

			}
			encontrou = Boolean.FALSE;
		}
	}
	
	private void salvarProduto(ParametrizacaoAmbienteDTO param) {
		List<ProdutoDTO> listaProdutos = produtoService.buscarInformacoesIntegracaoProduto(param.getCodLoja());
		for (ProdutoDTO produtoDTO : listaProdutos) {
			ResponseProdutoDTO response = produtoQueroDeliveryFeignClient.buscarProdutoPorCodBarras(produtoDTO.getCodBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
			if(!response.getR()) {
				CategoriaQueroDeliveryDTO categoriaEncontrada = categoriaQueroDeliveryService.buscarRegistroPorCodCategoriaConsinco(produtoDTO.getCodCategoria(),param.getAmbiente(), param.getCodLoja());
			    if(validarProdutoAntesSalvar(produtoDTO,categoriaEncontrada,param.getAmbiente(),param.getCodLoja())) {
			    	ProdutoCadastroQueroDeliveryDTO produto = new ProdutoCadastroQueroDeliveryDTO(produtoDTO.getNomeProduto(), categoriaEncontrada.getCodCategoriaQueroDelivery(), produtoDTO.getNomeProduto(), "ATIVO", produtoDTO.getPrecoVarejo(), BigDecimal.ZERO, produtoDTO.getCodBarras(), produtoDTO.getCodProduto().toString(), Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
					if(produtoQueroDeliveryFeignClient.adicionarProduto(produto,param.getPlaceId(),param.getToken(),URI.create(param.getUrl())).getR()) {
						produtoQueroDeliveryFeignClient.alterarControlaEstoque(new ProdutoAlterarControlaEstoqueDTO(Boolean.TRUE), produto.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
						logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ADICIONAR_PRODUTO,"Produto adicionado: " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null,produtoDTO.getNomeProduto(),param.getAmbiente(), param.getCodLoja()));
					}else {
						logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_NAO_ADICIONADO,"Produto não foi adicionado: " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null,produtoDTO.getNomeProduto(),param.getAmbiente(), param.getCodLoja()));
					}
			    }
			}else {
				validarPrecoProduto(response.getData(), produtoDTO, param);
				validarStatusProduto(response.getData(), produtoDTO, param);
			  //validarNomeEDescricaoProduto(response.getData(), produtoDTO, param);
				atualizarEstoqueProduto(response.getData(),produtoDTO, param);
				validarEstoqueParaAtualizacaoDeStatus(response.getData(),param);
				
			}
		}
	}

	private void atualizarEstoqueProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco,ParametrizacaoAmbienteDTO param) {
		Integer estoqueQueroDelivery = produtoQueroDeliveryFeignClient.buscarEstoque(produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl())).getData().getProduto().getQtdEstoque();
		Integer estoqueConsinco = produtoBaseConsinco.getQtdEstoqueMenorEmb();
		estoqueConsinco = estoqueConsinco < 1 ? 0 : estoqueConsinco;
		if(!estoqueQueroDelivery.equals(estoqueConsinco)) {
			Integer estoque = estoqueConsinco.equals(0) ?  estoqueQueroDelivery * (-1) : (estoqueConsinco - estoqueQueroDelivery);
			estoque = estoque > 9999 ? 9999 : estoque;
			produtoQueroDeliveryFeignClient.atualizarEstoqueProduto(new ProdutoAtualizarEstoqueDTO(estoque), produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_ESTOQUE_PRODUTO,"Estoque Atualizado. Valor antigo: " + estoqueQueroDelivery + "/ Valor novo: " + estoqueConsinco, produtoEncontrado.getCodigoBarras(), null, produtoEncontrado.getNome(),param.getAmbiente(), param.getCodLoja()));
		}
	}
	
	private void validarPrecoProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco,ParametrizacaoAmbienteDTO param) {
		if(produtoBaseConsinco.getPrecoVarejo().compareTo(produtoEncontrado.getPreco()) != 0) {
			produtoQueroDeliveryFeignClient.atualizarPrecoProduto(new ProdutoAtualizarPrecoDTO(produtoBaseConsinco.getPrecoVarejo(), produtoEncontrado.getPreco()), produtoEncontrado.getCodigoBarras(), param.getPlaceId(), param.getToken(),URI.create(param.getUrl()));
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_PRECO_PRODUTO,"Preço Atualizado. Valor antigo: " + produtoEncontrado.getPreco() + "/ Valor novo: " + produtoBaseConsinco.getPrecoVarejo(), produtoEncontrado.getCodigoBarras(), null, produtoEncontrado.getNome(), param.getAmbiente(), param.getCodLoja()));
		}
	}
	
	private void validarStatusProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco,ParametrizacaoAmbienteDTO param) {
		if(!produtoBaseConsinco.getStatusVenda().equals(STATUS_ATIVO_CONSINCO)) {
			produtoQueroDeliveryFeignClient.atualizarStatusProduto(new ProdutoAtualizarStatusDTO(STATUS_OCULTO), produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_STATUS_PRODUTO,"Status Atualizado. Novo status: " + STATUS_OCULTO, produtoEncontrado.getCodigoBarras(), null, produtoEncontrado.getNome(), param.getAmbiente(), param.getCodLoja()));
		}
	}
	
	private void validarNomeEDescricaoProduto(ResponseProdutoDadoDTO produtoEncontrado,ProdutoDTO produtoBaseConsinco,ParametrizacaoAmbienteDTO param) {
		if(!produtoBaseConsinco.getNomeProduto().equals(produtoEncontrado.getDescricao())) {
			produtoQueroDeliveryFeignClient.atualizarDescricaoProduto(new ProdutoAtualizarDescricaoDTO(produtoBaseConsinco.getNomeProduto()), produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
			produtoQueroDeliveryFeignClient.atualizarNomeProduto(new ProdutoAtualizarNomeDTO(MethodsUtils.capitalizarString(produtoBaseConsinco.getNomeProduto())), produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.ATUALIZAR_DESCRICAO_PRODUTO,"Nome/Descrição Atualizados. Valor antigo: " + produtoEncontrado.getDescricao() + "/ Valor novo: " + produtoBaseConsinco.getNomeProduto(), produtoEncontrado.getCodigoBarras(), null,produtoBaseConsinco.getNomeProduto(),param.getAmbiente(), param.getCodLoja()));

		}
	}

	private Boolean validarProdutoAntesSalvar(ProdutoDTO produtoDTO, CategoriaQueroDeliveryDTO categoria, String ambiente, Integer codLoja) {
		if(!validarSeProdutoPossuiCategoria(categoria)) {
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_SEM_CATEGORIA,"Produto sem categoria encontrado: : " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null, produtoDTO.getNomeProduto(),ambiente,codLoja));
			return Boolean.FALSE;
		}
		if(!validarCodigoBarrasDoProduto(produtoDTO)) {
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_SEM_COD_BARRAS,"Produto sem código de barras encontrado: : " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null, produtoDTO.getNomeProduto(),ambiente,codLoja));
			return Boolean.FALSE;
		}
		
		if(!validarPrecoDoProduto(produtoDTO)) {
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_PRECO_ZERADO,"Produto sem preço encontrado: : " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null, produtoDTO.getNomeProduto(),ambiente,codLoja));
			return Boolean.FALSE;
		}
		
		if(!validarSeProdutoPossuiEstoque(produtoDTO)) {
			logIntegracaoQueroDeliveryService.salvarLog(new LogIntegracaoQueroDelivery(new Date(), TipoLogIntegracaoEnum.PRODUTO_SEM_ESTOQUE,"Produto não importado por não ter estoque: " + produtoDTO.getNomeProduto(), produtoDTO.getCodBarras(), null, produtoDTO.getNomeProduto(),ambiente,codLoja));
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
	
	private Boolean validarSeProdutoPossuiEstoque(ProdutoDTO produtoDTO) {
		return produtoDTO.getQtdEstoqueMenorEmb() > 0;
	}
	
	private List<ResponseProdutoDadoDTO> buscarTodosProdutos(ParametrizacaoAmbienteDTO param){
		List<ResponseProdutoDadoDTO> list = new ArrayList<>();
		ResponseTodosProdutosDTO response = null;
		Integer offset = 0;
		while(true) {
			response = produtoQueroDeliveryFeignClient.buscarProdutos(offset, 50, param.getPlaceId(), param.getToken(),URI.create(param.getUrl()));
			if(response.getData() == null) {
				break;
			}
			list.addAll(response.getData().getProdutos());
			offset = offset + response.getData().getProdutos().size();
		}
		return list;
	}
	
	private void validarEstoqueParaAtualizacaoDeStatus(ResponseProdutoDadoDTO produtoEncontrado,ParametrizacaoAmbienteDTO param) {
		if(produtoQueroDeliveryFeignClient.buscarEstoque(produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl())).getData().getProduto().getQtdEstoque().equals(0)) {
			produtoQueroDeliveryFeignClient.atualizarStatusProduto(new ProdutoAtualizarStatusDTO(STATUS_OCULTO), produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
		}else {
			produtoQueroDeliveryFeignClient.atualizarStatusProduto(new ProdutoAtualizarStatusDTO(STATUS_ATIVO), produtoEncontrado.getCodigoBarras(),param.getPlaceId(),param.getToken(),URI.create(param.getUrl()));
		}
	}
	
}
