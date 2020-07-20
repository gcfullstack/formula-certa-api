package corp.gruposfa.novo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO;
import corp.gruposfa.novo.model.CategoriaQueroDelivery;
import corp.gruposfa.novo.model.dto.CategoriaQueroDeliveryDTO;
import corp.gruposfa.novo.repository.CategoriaQueroDeliveryRepository;
import corp.gruposfa.novo.service.CategoriaQueroDeliveryService;

@Service
public class CategoriaQueroDeliveryServiceImpl implements CategoriaQueroDeliveryService{

	private final CategoriaQueroDeliveryRepository categoriaQueroDeliveryRepository;

	public CategoriaQueroDeliveryServiceImpl(CategoriaQueroDeliveryRepository categoriaQueroDeliveryRepository) {
		this.categoriaQueroDeliveryRepository = categoriaQueroDeliveryRepository;
	}

	@Override
	public CategoriaQueroDelivery salvarRegistro(CategoriaQueroDelivery obj) {
		return categoriaQueroDeliveryRepository.saveAndFlush(obj);
	}

	@Override
	public List<CategoriaCompareDTO> buscarRegistros(String ambiente, Integer codLoja) {
		return categoriaQueroDeliveryRepository.buscarRegistros(ambiente,codLoja);
	}

	@Override
	public CategoriaQueroDeliveryDTO buscarRegistroPorCodCategoriaConsinco(Integer codCategoria, String ambiente, Integer codLoja) {
		return categoriaQueroDeliveryRepository.buscarRegistroPorCodCategoriaConsinco(codCategoria,ambiente,codLoja);
	}

	@Override
	public void excluirRegistroPorCodCategoriaConsinco(Integer codCategoria, String ambiente, Integer codLoja) {
		categoriaQueroDeliveryRepository.excluirRegistroPorCodCategoriaConsinco(codCategoria,ambiente, codLoja);
	}

	
}
