package corp.formulacerta.integracao.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.service.IntegradorFormulaCertaService;

@RestController
@RequestMapping("/api/public/integrar")
public class IntegracaoFormulaCertaController {
	
	private final IntegradorFormulaCertaService service;
	
	
	public IntegracaoFormulaCertaController(IntegradorFormulaCertaService service) {
		super();
		this.service = service;
	}


	@PostMapping
	public ResponseEntity<?> executarIntegracao(){
		service.executarIntegracao();
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> ok(){
		System.out.println("working");
		return  new ResponseEntity<>(HttpStatus.OK);
	}
}
