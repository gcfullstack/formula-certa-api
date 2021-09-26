package corp.formulacerta.integracao.mirror.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.mirror.model.OrcTrail;
import corp.formulacerta.integracao.mirror.repository.OrcTrailRepository;
import corp.formulacerta.integracao.mirror.service.OrcTrailService;
import corp.formulacerta.integracao.model.dto.OrcamentoDTO;

@Service
public class OrcTrailServiceImpl implements OrcTrailService {
	
	private final OrcTrailRepository orcTrailRepository; 
	
	public OrcTrailServiceImpl(OrcTrailRepository orcTrailRepository) {
		this.orcTrailRepository = orcTrailRepository;
	}

	@Override
	public OrcTrail saveEntity(OrcTrail orc) {
		return orcTrailRepository.saveAndFlush(orc);
	}

	@Override
	public List<OrcamentoDTO> findAll() {
		return orcTrailRepository.buscarTodos();
	}

	public void updateIdProdutoTray(Integer idOrc, String idProdutoTray) {
		orcTrailRepository.updateIdProdutoTray(idOrc, idProdutoTray);
	}


}
