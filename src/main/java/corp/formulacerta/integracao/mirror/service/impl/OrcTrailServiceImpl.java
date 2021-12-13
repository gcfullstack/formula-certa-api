package corp.formulacerta.integracao.mirror.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.mirror.model.OrcTrail;
import corp.formulacerta.integracao.mirror.repository.OrcTrailRepository;
import corp.formulacerta.integracao.mirror.service.OrcTrailService;
import corp.formulacerta.integracao.model.dto.OrcamentoDTO;
import corp.formulacerta.integracao.utils.ConstantsUtils;
import corp.formulacerta.integracao.utils.MethodsUtils;

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
	public List<OrcamentoDTO> buscarTodos() {
		return orcTrailRepository.buscarTodos();
	}

	public void updateIdProdutoTray(Integer idOrc, String idProdutoTray) {
		orcTrailRepository.updateIdProdutoTray(idOrc, idProdutoTray);
	}

	@Override
	public List<Integer> buscarNumOrcamentosPorData(Date data) {
		return orcTrailRepository.buscarNumOrcamentosPorData(MethodsUtils.formatarDataString(data, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD));
	}

	@Override
	public List<OrcTrail> findAll() {
		return orcTrailRepository.findAll();
	}

	@Override
	public List<OrcTrail> buscarOrcamentosNaoIntegrados(Date data) {
		return orcTrailRepository.buscarOrcamentosNaoIntegrados(data);
	}

}