package corp.formulacerta.integracao.mirror.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.n8n.service.OrcamentoN8NService;

@RestController
@RequestMapping("/api/orcamento-trail")
public class OrcTrailController {

	
	private final OrcamentoN8NService orcamentoN8NService;

	public OrcTrailController(OrcamentoN8NService orcamentoN8NService) {
		super();
		this.orcamentoN8NService = orcamentoN8NService;
	}

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		return ResponseEntity.ok(orcamentoN8NService.buscarOrcamento());
	}
	
	@GetMapping("/id-orcamento/{idOrcamento}")
	public ResponseEntity<?> buscarPorIdOrcamento(@PathVariable("idOrcamento") String idOrcamento) {
		return ResponseEntity.ok(orcamentoN8NService.buscarOrcamentoPorIdOrcamento(idOrcamento));
	}
	
	/**
	 * Esse endpoint traz todas as colunas do banco
	 */
}
