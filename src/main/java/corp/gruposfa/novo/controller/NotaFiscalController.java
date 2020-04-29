package corp.gruposfa.novo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.gruposfa.novo.dto.ModeloArquivo;
import corp.gruposfa.novo.service.NotaFiscalService;

@RestController
@RequestMapping("/api/notafiscal")
public class NotaFiscalController {

	private final NotaFiscalService notaFiscalService;
	
	public NotaFiscalController(NotaFiscalService notaFiscalService) {
		this.notaFiscalService = notaFiscalService;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/upload")
	public ResponseEntity uploadArquivos(@RequestBody List<ModeloArquivo> listaArquivos) {
		notaFiscalService.uploadNotasFiscais(listaArquivos);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
