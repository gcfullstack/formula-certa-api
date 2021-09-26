package corp.formulacerta.integracao.tray.service;

import corp.formulacerta.integracao.tray.model.dto.ProdutoCriadoDTO;
import corp.formulacerta.integracao.tray.model.dto.ProdutoDTO;

public interface ProdutoService {
	
	public ProdutoCriadoDTO cadastrarProduto(ProdutoDTO produto);

}
