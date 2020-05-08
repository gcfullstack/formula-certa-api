package corp.gruposfa.novo.consinco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import corp.gruposfa.novo.consinco.service.IntegracaoQueroDeliveryService;

@RestController
@RequestMapping("/api/quero-delivery")
public class QueroDeliveryController {
	
	private final IntegracaoQueroDeliveryService integracaoQueroDeliveryService;
	
	public QueroDeliveryController(IntegracaoQueroDeliveryService integracaoQueroDeliveryService) {
		this.integracaoQueroDeliveryService = integracaoQueroDeliveryService;
	}
	
	@PostMapping("/executar-integracao")
	public ResponseEntity<?> executarIntegracao() {
		System.out.println("Iniciou");
		integracaoQueroDeliveryService.executarJobIntegracao();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
