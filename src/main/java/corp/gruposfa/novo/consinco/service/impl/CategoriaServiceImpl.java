package corp.gruposfa.novo.consinco.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.repository.CategoriaRepository;
import corp.gruposfa.novo.consinco.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;
	
	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<String> buscarNomeCategorias() {
		return categoriaRepository.buscarNomeCategorias();
	}

}
