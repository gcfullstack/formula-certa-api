package corp.formulacerta.integracao.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.model.dto.OrcamentoDTO;
import corp.formulacerta.integracao.service.IntegradorFormulaCertaService;
import corp.formulacerta.integracao.service.OrcamentoFormulaCertaService;

@RestController
@RequestMapping("/api/public/integrar")
public class IntegracaoFormulaCertaController {
	
	private final IntegradorFormulaCertaService service;
	
	private final OrcamentoFormulaCertaService orcamentoFormulaCertaService;
	
	public IntegracaoFormulaCertaController(IntegradorFormulaCertaService service,OrcamentoFormulaCertaService orcamentoFormulaCertaService) {
		super();
		this.service = service;
		this.orcamentoFormulaCertaService = orcamentoFormulaCertaService;
	}


	@PostMapping
	public ResponseEntity<?> executarIntegracao(){
		service.executarIntegracao();
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> ok(){
		System.out.println("working");
		List<OrcamentoDTO> orcamentos = orcamentoFormulaCertaService.findOrcamentoBiggerThanCustomID(975413);
		return  ResponseEntity.ok(orcamentos);
	}
}
