package corp.gruposfa.novo.consinco.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO;
import corp.gruposfa.novo.consinco.repository.CategoriaRepository;
import corp.gruposfa.novo.consinco.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<CategoriaCompareDTO> buscarCategorias() {
		return categoriaRepository.buscarNomeCategorias().stream().map(x -> new CategoriaCompareDTO(x)).collect(Collectors.toList());
	}

}
