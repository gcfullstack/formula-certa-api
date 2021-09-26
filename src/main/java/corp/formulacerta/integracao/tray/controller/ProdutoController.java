package corp.formulacerta.integracao.tray.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import corp.formulacerta.integracao.tray.model.dto.ProdutoCriadoDTO;
import corp.formulacerta.integracao.tray.model.dto.ProdutoDTO;
import corp.formulacerta.integracao.tray.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	private final ProdutoService produtoService;

	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	@GetMapping
	public ResponseEntity<ProdutoCriadoDTO> cadastrarProduto(@RequestBody ProdutoDTO dto) {
		ProdutoCriadoDTO post = produtoService.cadastrarProduto(dto);
		return ResponseEntity.ok(post);
	}

}
