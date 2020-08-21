package corp.gruposfa.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teste")
public class TesteController {
	

	@GetMapping
	public ResponseEntity<String> testGet(){
		return  ResponseEntity.ok("get ok");
	}
	
	@PostMapping
	public ResponseEntity<String> testPost(){
		return  ResponseEntity.ok("post ok");
	}
	
	@PutMapping
	public ResponseEntity<String> testPut(){
		return  ResponseEntity.ok("put ok");
	}
	
	@DeleteMapping
	public ResponseEntity<String> testDelete(){
		return  ResponseEntity.ok("delete ok");
	}
	
	
}
