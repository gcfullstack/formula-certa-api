package corp.gruposfa.novo.consinco.feign;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import corp.gruposfa.novo.consinco.model.dto.CategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseAddCategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseCategoriaDTO;

@FeignClient(name = "categoriaQueroDeliveryFeignClient", url = "${application.quero-delivery.url-api-hml}")
public interface CategoriaQueroDeliveryFeignClient {
	
	 @RequestMapping(method = RequestMethod.GET, value="/categoria", headers = {"User-Agent=api-novo"})
	 ResponseCategoriaDTO buscarCategorias(URI baseUrl,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token);
	 
	 @RequestMapping(method = RequestMethod.POST, value="/categoria", headers = {"User-Agent=api-novo"})
	 ResponseAddCategoriaDTO adicionarCategoria(@RequestBody CategoriaDTO categoria, @RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 
	 @RequestMapping(method = RequestMethod.DELETE, value="/categoria", headers = {"User-Agent=api-novo"})
	 ResponseCategoriaDTO excluirCategoria(@RequestParam("categoriaId") String categoriaId,@RequestParam("placeId") String placeId,@RequestHeader(value = "authorization", required = true) String token,URI baseUrl);
	 

}
