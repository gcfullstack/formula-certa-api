package corp.gruposfa.novo.consinco.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.model.dto.ProdutoDTO;
import corp.gruposfa.novo.consinco.repository.ProdutoRepository;
import corp.gruposfa.novo.consinco.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private final ProdutoRepository produtoRepository;
	
	public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@Override
	public List<ProdutoDTO> buscarInformacoesIntegracaoProduto(Integer codLoja) {
		return produtoRepository.buscarProdutos(codLoja).stream().map(x -> new ProdutoDTO(x)).collect(Collectors.toList());
	}

}
