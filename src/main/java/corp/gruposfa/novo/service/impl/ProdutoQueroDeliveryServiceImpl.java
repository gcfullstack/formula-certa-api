package corp.gruposfa.novo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.model.ProdutoQueroDelivery;
import corp.gruposfa.novo.model.dto.ProdutoQueroDeliveryDTO;
import corp.gruposfa.novo.repository.ProdutoQueroDeliveryRepository;
import corp.gruposfa.novo.service.ProdutoQueroDeliveryService;

@Service
public class ProdutoQueroDeliveryServiceImpl implements ProdutoQueroDeliveryService {

	private final ProdutoQueroDeliveryRepository produtoQueroDeliveryRepository;
	
	public ProdutoQueroDeliveryServiceImpl(ProdutoQueroDeliveryRepository produtoQueroDeliveryRepository) {
		this.produtoQueroDeliveryRepository = produtoQueroDeliveryRepository;
	}

	@Override
	public List<ProdutoQueroDeliveryDTO> buscarProdutosPorCodLoja(Integer codLoja) {
		return produtoQueroDeliveryRepository.buscarProdutosPorCodLoja(codLoja);
	}

	@Override
	public void atualizarEstoque(Integer codLoja, Integer estoque, String codBarra) {
		produtoQueroDeliveryRepository.atualizarEstoque(codLoja, estoque, codBarra);
	}

	@Override
	public void salvarProduto(ProdutoQueroDelivery produto) {
		produtoQueroDeliveryRepository.saveAndFlush(produto);
	}

}
