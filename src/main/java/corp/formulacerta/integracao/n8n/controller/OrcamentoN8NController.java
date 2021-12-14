package corp.formulacerta.integracao.n8n.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.n8n.model.dto.LogConsultaFormulaCertaDTO;
import corp.formulacerta.integracao.n8n.service.LogOrcamentoN8NService;
import corp.formulacerta.integracao.n8n.service.impl.OrcamentoN8NServiceImpl;


@RestController
@RequestMapping("/api/n8n")
public class OrcamentoN8NController {
	
	private final OrcamentoN8NServiceImpl orcamentoN8NServiceImpl;
	
	private final LogOrcamentoN8NService logService;
	
	public OrcamentoN8NController(OrcamentoN8NServiceImpl orcamentoN8NServiceImpl, LogOrcamentoN8NService logService) {
		super();
		this.orcamentoN8NServiceImpl = orcamentoN8NServiceImpl;
		this.logService = logService;
	}

	@GetMapping
	public ResponseEntity<LogConsultaFormulaCertaDTO> teste(){
		return ResponseEntity.ok(logService.buscarUltimoRegistroLog());
	}
	
	@PostMapping
	public ResponseEntity<?> teste2(@RequestBody LogConsultaFormulaCertaDTO dto){
		return ResponseEntity.ok(logService.salvarLogOrcamento(dto));
	}
	
	private Date getCurrentDateMinusDays(Integer amountDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, (amountDays * -1));
		return cal.getTime();
	}

}
