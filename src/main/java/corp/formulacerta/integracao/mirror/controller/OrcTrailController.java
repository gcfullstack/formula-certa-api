package corp.formulacerta.integracao.mirror.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.mirror.service.OrcTrailService;
import corp.formulacerta.integracao.n8n.service.OrcamentoN8NService;

@RestController
@RequestMapping("/api/orcamento-trail")
public class OrcTrailController {

	private final OrcTrailService orcTrailService;
	
	private final OrcamentoN8NService orcamentoN8NService;

	public OrcTrailController(OrcTrailService orcTrailService,OrcamentoN8NService orcamentoN8NService) {
		super();
		this.orcTrailService = orcTrailService;
		this.orcamentoN8NService = orcamentoN8NService;
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
