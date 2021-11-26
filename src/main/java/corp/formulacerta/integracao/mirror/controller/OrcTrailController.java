package corp.formulacerta.integracao.mirror.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.mirror.service.OrcTrailService;

@RestController
@RequestMapping("/api/orcamento-trail")
public class OrcTrailController {

	private final OrcTrailService orcTrailService;

	public OrcTrailController(OrcTrailService orcTrailService) {
		super();
		this.orcTrailService = orcTrailService;
	}

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok(orcTrailService.buscarTodos());
	}
	
	/**
	 * Esse endpoint traz todas as colunas do banco
	 */
	@GetMapping("/all")
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(orcTrailService.findAll());
	}
}
