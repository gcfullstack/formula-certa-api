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
	public List<CategoriaCompareDTO> buscarRegistros(String ambiente) {
		return categoriaQueroDeliveryRepository.buscarRegistros(ambiente);
	}

	@Override
	public CategoriaQueroDeliveryDTO buscarRegistroPorCodCategoriaConsinco(Integer codCategoria, String ambiente) {
		return categoriaQueroDeliveryRepository.buscarRegistroPorCodCategoriaConsinco(codCategoria,ambiente);
	}

	@Override
	public void excluirRegistroPorCodCategoriaConsinco(Integer codCategoria, String ambiente) {
		categoriaQueroDeliveryRepository.excluirRegistroPorCodCategoriaConsinco(codCategoria,ambiente);
	}

	
}
