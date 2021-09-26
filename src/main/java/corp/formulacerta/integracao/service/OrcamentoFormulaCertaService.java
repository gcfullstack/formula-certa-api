package corp.formulacerta.integracao.service;

import java.util.List;

import corp.formulacerta.integracao.model.dto.OrcamentoDTO;

public interface OrcamentoFormulaCertaService {
	
	List<OrcamentoDTO> findOrcamentoBiggerThanCustomID(Integer lastId);
}
