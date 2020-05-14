package corp.gruposfa.novo.consinco.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import corp.gruposfa.novo.consinco.model.dto.CategoriaDTO;
import corp.gruposfa.novo.consinco.model.dto.ResponseCategoriaDTO;

@FeignClient(name = "categoriaQueroDeliveryFeignClient", url = "${application.quero-delivery.url-api}")
public interface CategoriaQueroDeliveryFeignClient {
	
	 @RequestMapping(method = RequestMethod.GET, value="/categoria?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseCategoriaDTO buscarCategorias();
	 
	 @RequestMapping(method = RequestMethod.POST, value="/categoria?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseCategoriaDTO adicionarCategoria(@RequestBody CategoriaDTO categoria);
	 
	 @RequestMapping(method = RequestMethod.PUT, value="/categoria?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseCategoriaDTO editarCategoria(@RequestBody CategoriaDTO categoria, @RequestParam("categoriaId") String categoriaId);
	 
	 @RequestMapping(method = RequestMethod.DELETE, value="/categoria?placeId=${application.quero-delivery.place-id}", headers = {"authorization=${application.quero-delivery.token}", "User-Agent=api-novo"})
	 ResponseCategoriaDTO excluirCategoria(@RequestParam("categoriaId") String categoriaId);
	 

}
