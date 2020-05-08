package corp.gruposfa.novo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import corp.gruposfa.novo.consinco.feign.CategoriaQueroDeliveryFeignClient;
import corp.gruposfa.novo.consinco.model.dto.ResponseCategoriaDTO;
import corp.gruposfa.novo.feign.ServicoIntegracaoFeignClient;
import corp.gruposfa.novo.service.NotaFiscalService;
import corp.gruposfa.novo.service.TesteService;

@RestController
@RequestMapping("/api/teste")
public class TesteController {
	
	private final TesteService service;
	
	private final NotaFiscalService notaFiscalRepository;
	
	private final CategoriaQueroDeliveryFeignClient categoriaQueroDeliveryFeignClient;
	
	private final ServicoIntegracaoFeignClient servicoIntegracaoFeignClient;
	
	public TesteController(TesteService service,NotaFiscalService notaFiscalRepository,CategoriaQueroDeliveryFeignClient categoriaQueroDeliveryFeignClient,ServicoIntegracaoFeignClient servicoIntegracaoFeignClient) {
		this.service = service;
		this.notaFiscalRepository = notaFiscalRepository;
		this.categoriaQueroDeliveryFeignClient = categoriaQueroDeliveryFeignClient;
		this.servicoIntegracaoFeignClient = servicoIntegracaoFeignClient;
	}

	@GetMapping
	public ResponseEntity<ResponseCategoriaDTO> teste(){
		ResponseCategoriaDTO buscarNomesCategorias = null;
		try {
			servicoIntegracaoFeignClient.salvarInicioDeExecucaoDeServico(18);
			servicoIntegracaoFeignClient.alterarStatusExecucaoJob(18,Boolean.TRUE);
			servicoIntegracaoFeignClient.salvarFimDeExecucaoDeServico(18);
			servicoIntegracaoFeignClient.alterarStatusExecucaoJob(18,Boolean.FALSE);
			String verificarStatusExecucaoJob = servicoIntegracaoFeignClient.verificarStatusExecucaoJob(18);
			Boolean valueOf = Boolean.valueOf(verificarStatusExecucaoJob);
			System.out.println(verificarStatusExecucaoJob);
			System.out.println(verificarStatusExecucaoJob);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  ResponseEntity.ok(buscarNomesCategorias);
	}
	
	
}
