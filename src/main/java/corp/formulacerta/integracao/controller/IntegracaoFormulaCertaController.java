package corp.formulacerta.integracao.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.model.dto.OrcamentoDTO;
import corp.formulacerta.integracao.properties.TrayProperties;
import corp.formulacerta.integracao.service.IntegradorFormulaCertaService;
import corp.formulacerta.integracao.service.OrcamentoFormulaCertaService;

@RestController
@RequestMapping("/api/integrar")
public class IntegracaoFormulaCertaController {
	
	private final IntegradorFormulaCertaService service;
	
	private final OrcamentoFormulaCertaService orcamentoFormulaCertaService;
	
	private final TrayProperties trayProperties;
	
	public IntegracaoFormulaCertaController(IntegradorFormulaCertaService service,OrcamentoFormulaCertaService orcamentoFormulaCertaService, TrayProperties trayProperties) {
		super();
		this.service = service;
		this.orcamentoFormulaCertaService = orcamentoFormulaCertaService;
		this.trayProperties = trayProperties;
	}


	@PostMapping
	public ResponseEntity<?> executarIntegracao(){
		service.executarIntegracao();
		return  new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<?> ok(){
		System.out.println(trayProperties.getEnvironment());
		List<OrcamentoDTO> orcamentos = orcamentoFormulaCertaService.findOrcamentoBiggerThanCustomID(975413);
		return  ResponseEntity.ok(orcamentos);
	}
}
