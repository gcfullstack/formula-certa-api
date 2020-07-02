package corp.gruposfa.novo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.context.Context;

import corp.gruposfa.novo.mapper.NotaFiscalMapper;
import corp.gruposfa.novo.model.dto.ModeloArquivo;
import corp.gruposfa.novo.model.dto.NotaFiscalDTO;
import corp.gruposfa.novo.model.dto.NotaFiscalFiltroDTO;
import corp.gruposfa.novo.service.NotaFiscalService;
import corp.gruposfa.novo.utils.EmailHtmlSender;

@RestController
@RequestMapping("/api/notafiscal")
public class NotaFiscalController {

	private final NotaFiscalService notaFiscalService;
	private final NotaFiscalMapper notaFiscalMapper;
	
	@Autowired
    private EmailHtmlSender emailHtmlSender;
	
	public NotaFiscalController(NotaFiscalService notaFiscalService, NotaFiscalMapper notaFiscalMapper, EmailHtmlSender emailHtmlSender) {
		this.notaFiscalService = notaFiscalService;
		this.notaFiscalMapper = notaFiscalMapper;
		this.emailHtmlSender = emailHtmlSender;
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/upload")
	public ResponseEntity uploadArquivos(@RequestBody List<ModeloArquivo> listaArquivos) {
		notaFiscalService.uploadNotasFiscais(listaArquivos);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<List<NotaFiscalDTO>> getNotasFiscaisUsuario(@PathVariable("usuario") Integer usuario) {
		return ResponseEntity.ok(notaFiscalService.getNotasFiscaisUsuario(usuario));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/aprovar/{id}/{usuario}")
	public ResponseEntity aprovar(@PathVariable("id") Integer id, @PathVariable("usuario") String usuario) {
		try {
			notaFiscalService.aprovar(id, usuario);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/reprovar/{id}/{usuario}/{motivo}")
	public ResponseEntity reprovar(@PathVariable("id") Integer id, @PathVariable("usuario") String usuario, @PathVariable("motivo") String motivo) {
		try {
			notaFiscalService.reprovar(id, usuario,  motivo);
			Context context = new Context();
			NotaFiscalDTO nf = notaFiscalService.getNotaFiscalId(id);
			context.setVariable("nf", nf);
			//emailHtmlSender.send(nf.getUsuario().contains("@") ? nf.getUsuario() : notaFiscalService.getEmailUsuarioInterno(usuario), "Nota Fiscal Reprovada - NOVO", "email/emailNFReprovada", context);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody NotaFiscalDTO notaFiscalDTO) {
		try {
			if(notaFiscalDTO.getId() == null) {
				notaFiscalDTO.setEnviado(0);
				notaFiscalDTO.setDataUpload(new Date());
			}
			notaFiscalService.salvar(notaFiscalMapper.toEntity(notaFiscalDTO));
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/enviar")
	public ResponseEntity enviar(@RequestBody NotaFiscalDTO notaFiscalDTO) {
		try {
			if(notaFiscalDTO.getId() != null) {
				notaFiscalDTO.setEnviado(1);
				notaFiscalDTO.setDataEnvio(new Date());
				notaFiscalService.salvar(notaFiscalMapper.toEntity(notaFiscalDTO));
				return new ResponseEntity<>(HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id da nota enviada é inválido");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/descartar")
	public ResponseEntity descartar(@RequestBody NotaFiscalDTO notaFiscalDTO) {
		try {
			if(notaFiscalDTO.getId() != null) {
				notaFiscalDTO.setEnviado(2);
				notaFiscalDTO.setDataEnvio(new Date());
				notaFiscalService.salvar(notaFiscalMapper.toEntity(notaFiscalDTO));
				return new ResponseEntity<>(HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id da nota enviada é inválido");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/buscar")
	public ResponseEntity<List<NotaFiscalDTO>> buscar(@RequestBody NotaFiscalFiltroDTO notaFiscalFiltroDTO) {
		try {
			return ResponseEntity.ok(notaFiscalService.buscar(notaFiscalFiltroDTO));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
}
