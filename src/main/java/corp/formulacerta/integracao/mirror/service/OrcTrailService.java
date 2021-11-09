package corp.formulacerta.integracao.mirror.service;

import java.util.Date;
import java.util.List;

import corp.formulacerta.integracao.mirror.model.OrcTrail;
import corp.formulacerta.integracao.model.dto.OrcamentoDTO;

public interface OrcTrailService {

	OrcTrail saveEntity(OrcTrail orc);
	
	List<OrcamentoDTO> findAll();
	
	void updateIdProdutoTray(Integer id, String idProdutoTray);
	
	List<Integer> buscarNumOrcamentosPorData(Date data);
	
}
