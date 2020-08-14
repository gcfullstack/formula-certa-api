package corp.gruposfa.novo.service;

import java.util.List;

import corp.gruposfa.novo.model.ProdutoQueroDelivery;
import corp.gruposfa.novo.model.dto.ProdutoQueroDeliveryDTO;

public interface ProdutoQueroDeliveryService {

	List<ProdutoQueroDeliveryDTO> buscarProdutosPorCodLoja(Integer codLoja);
	
	void atualizarEstoque(Integer codLoja,Integer estoque, String codBarra);
	
	void salvarProduto(ProdutoQueroDelivery produto);


}
