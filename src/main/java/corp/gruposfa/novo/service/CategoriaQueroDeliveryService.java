package corp.gruposfa.novo.service;

import java.util.List;

import corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO;
import corp.gruposfa.novo.model.CategoriaQueroDelivery;
import corp.gruposfa.novo.model.dto.CategoriaQueroDeliveryDTO;

public interface CategoriaQueroDeliveryService {
	
	CategoriaQueroDelivery salvarRegistro(CategoriaQueroDelivery obj);
	
	List<CategoriaCompareDTO> buscarRegistros();
	
	CategoriaQueroDeliveryDTO buscarRegistroPorCodCategoriaConsinco(Integer codCategoria);
	
	CategoriaQueroDeliveryDTO buscarRegistroPorCodCategoriaQueroDelivery(String codCategoria);
	
	void excluirRegistroPorCodCategoriaConsinco(Integer codCategoria);
	

}
