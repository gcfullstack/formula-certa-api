package corp.gruposfa.novo.consinco.feign;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

@FeignClient(name = "produtoQueroDeliveryFeignClient", url = "${application.quero-delivery.url-api-hml}")
public interface ProdutoQueroDeliveryFeignClient {
	 
	 @RequestMapping(method = RequestMethod.GET, value="/produto", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO buscarProdutoPorCodBarras(@RequestParam("codigoBarras") String codigoBarras, @RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.POST, value="/produto", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO adicionarProduto(@RequestBody ProdutoCadastroQueroDeliveryDTO produto, @RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/status", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarStatusProduto(@RequestBody ProdutoAtualizarStatusDTO status,@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/preco", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarPrecoProduto(@RequestBody ProdutoAtualizarPrecoDTO preco,@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/lancar-estoque", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarEstoqueProduto(@RequestBody ProdutoAtualizarEstoqueDTO estoque,@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/descricao", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarDescricaoProduto(@RequestBody ProdutoAtualizarDescricaoDTO descricao,@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);

	 @RequestMapping(method = RequestMethod.PUT, value="/produto/nome", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO atualizarNomeProduto(@RequestBody ProdutoAtualizarNomeDTO nome,@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.GET, value="/produto/estoque", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO buscarEstoque(@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.GET, value="/produto", headers = {"User-Agent=api-novo"})
	 ResponseTodosProdutosDTO buscarProdutos(@RequestParam("offset") Integer offset,@RequestParam("limit") Integer limit,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.DELETE, value="/produto", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO excluirProdutoPorCodigoBarras(@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/produto/controla-estoque", headers = {"User-Agent=api-novo"})
	 ResponseProdutoDTO alterarControlaEstoque(@RequestBody ProdutoAlterarControlaEstoqueDTO dto,@RequestParam("codigoBarras") String codigoBarras,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
}
