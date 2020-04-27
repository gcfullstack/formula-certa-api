package corp.gruposfa.novo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.model.EntityTest;
import corp.gruposfa.novo.repository.TesteRepository;
import corp.gruposfa.novo.service.TesteService;

@Service
public class TesteServiceImpl implements TesteService{
	
	private final TesteRepository tRepo;
	
	public TesteServiceImpl(TesteRepository tRepo) {
		this.tRepo = tRepo;
	}

	@Override
	public List<EntityTest> getAll() {
		return tRepo.findAll();
	}

}
