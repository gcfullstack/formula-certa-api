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
	public List<CategoriaCompareDTO> buscarRegistros() {
		return categoriaQueroDeliveryRepository.buscarRegistros();
	}

	@Override
	public CategoriaQueroDeliveryDTO buscarRegistroPorCodCategoriaConsinco(Integer codCategoria) {
		return categoriaQueroDeliveryRepository.buscarRegistroPorCodCategoriaConsinco(codCategoria);
	}

	@Override
	public void excluirRegistroPorCodCategoriaConsinco(Integer codCategoria) {
		categoriaQueroDeliveryRepository.excluirRegistroPorCodCategoriaConsinco(codCategoria);
	}

	@Override
	public CategoriaQueroDeliveryDTO buscarRegistroPorCodCategoriaQueroDelivery(String codCategoria) {
		return categoriaQueroDeliveryRepository.buscarRegistroPorCodCategoriaQueroDelivery(codCategoria);
	}
	
}
