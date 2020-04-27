package corp.gruposfa.novo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.gruposfa.novo.model.EntityTest;
import corp.gruposfa.novo.service.TesteService;

@RestController
@RequestMapping("/api/teste")
public class TesteController {

	private final TesteService service;
	
	public TesteController(TesteService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<EntityTest>> teste(){
		return  ResponseEntity.ok(service.getAll());
	}
	
}
