package corp.gruposfa.novo.consinco.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import corp.gruposfa.novo.consinco.model.dto.ProdutoAlterarControlaEstoqueDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarDescricaoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarEstoqueDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarNomeDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarPrecoDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoAtualizarStatusDTO;
import corp.gruposfa.novo.consinco.model.dto.ProdutoCadastroQueroDeliveryDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseProdutoDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseTodosProdutosDTO;

@FeignClient(name = "produtoQueroDeliveryFeignClient", url = "${application.quero-delivery.url-api}")
public interface ProdutoQueroDeliveryFeignClient {
	 
	 @RequestMapping(method = RequestMethod.GET, value="/produto?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO buscarProdutoPorCodBarras(@RequestParam("codigoBarras") String codigoBarras);
	 
	 @RequestMapping(method = RequestMethod.POST, value="/produto?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO adicionarProduto(@RequestBody ProdutoCadastroQueroDeliveryDTO produto);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/status?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarStatusProduto(@RequestBody ProdutoAtualizarStatusDTO status,@RequestParam("codigoBarras") String codigoBarras);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/preco?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarPrecoProduto(@RequestBody ProdutoAtualizarPrecoDTO preco,@RequestParam("codigoBarras") String codigoBarras);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/lancar-estoque?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarEstoqueProduto(@RequestBody ProdutoAtualizarEstoqueDTO estoque,@RequestParam("codigoBarras") String codigoBarras);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/descricao?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarDescricaoProduto(@RequestBody ProdutoAtualizarDescricaoDTO descricao,@RequestParam("codigoBarras") String codigoBarras);

	 @RequestMapping(method = RequestMethod.PUT, value="/produto/nome?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarNomeProduto(@RequestBody ProdutoAtualizarNomeDTO nome,@RequestParam("codigoBarras") String codigoBarras);
	 
	 @RequestMapping(method = RequestMethod.GET, value="/produto/estoque?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO buscarEstoque(@RequestParam("codigoBarras") String codigoBarras);
	 
	 @RequestMapping(method = RequestMethod.GET, value="/produto?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseTodosProdutosDTO buscarProdutos(@RequestParam("offset") Integer offset,@RequestParam("limit") Integer limit);
	 
	 @RequestMapping(method = RequestMethod.DELETE, value="/produto?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO excluirProdutoPorCodigoBarras(@RequestParam("codigoBarras") String codigoBarras);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/controla-estoque?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseProdutoDTO alterarControlaEstoque(@RequestBody ProdutoAlterarControlaEstoqueDTO dto,@RequestParam("codigoBarras") String codigoBarras);
	 
}
