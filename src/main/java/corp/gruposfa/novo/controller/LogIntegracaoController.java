package corp.gruposfa.novo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.gruposfa.novo.consinco.service.LogIntegracaoQueroDeliveryService;
import corp.gruposfa.novo.model.dto.FiltroLogIntegracaoDTO;
import corp.gruposfa.novo.model.dto.ListagemLogIntegracaoDTO;
import corp.gruposfa.novo.model.dto.TipoLogDTO;

@RestController
@RequestMapping("/api/log/integracao/quero-delivery")
public class LogIntegracaoController {
	
	private final LogIntegracaoQueroDeliveryService logIntegracaoQueroDeliveryService;
	
	public LogIntegracaoController(LogIntegracaoQueroDeliveryService logIntegracaoQueroDeliveryService) {
		this.logIntegracaoQueroDeliveryService = logIntegracaoQueroDeliveryService;
	}

	@PostMapping
	public ResponseEntity<List<ListagemLogIntegracaoDTO>> buscarLogsIntegracao(@RequestBody FiltroLogIntegracaoDTO filtro){
		return  ResponseEntity.ok(logIntegracaoQueroDeliveryService.buscarLogs(filtro));
	}
	
	@GetMapping("/tipo-log")
	public ResponseEntity<List<TipoLogDTO>> buscarTiposLog(){
		return  ResponseEntity.ok(logIntegracaoQueroDeliveryService.getListaTipoLogs());
	}
	
}
