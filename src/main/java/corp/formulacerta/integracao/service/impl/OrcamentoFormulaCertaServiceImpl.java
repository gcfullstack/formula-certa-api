package corp.formulacerta.integracao.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.model.dto.OrcamentoDTO;
import corp.formulacerta.integracao.repository.OrcamentoFormulaCertaRepository;
import corp.formulacerta.integracao.service.OrcamentoFormulaCertaService;

@Service
public class OrcamentoFormulaCertaServiceImpl implements OrcamentoFormulaCertaService {
	
	private final OrcamentoFormulaCertaRepository orcamentoFormulaCertaRepository;

	public OrcamentoFormulaCertaServiceImpl(OrcamentoFormulaCertaRepository orcamentoFormulaCertaRepository) {
		this.orcamentoFormulaCertaRepository = orcamentoFormulaCertaRepository;
	}

	@Override
	public List<OrcamentoDTO> findOrcamentoBiggerThanCustomID(Integer lastId) {
		return orcamentoFormulaCertaRepository.findOrcamentoBiggerThanCustomID(lastId).stream().map(orc -> new OrcamentoDTO(orc)).collect(Collectors.toList());
	}

}
