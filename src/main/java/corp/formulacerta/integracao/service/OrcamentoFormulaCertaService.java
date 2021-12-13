package corp.formulacerta.integracao.service;

import java.util.Date;
import java.util.List;
import corp.formulacerta.integracao.model.dto.OrcamentoDTO;

public interface OrcamentoFormulaCertaService {
	
	List<OrcamentoDTO> findOrcamentoBiggerThanCustomID(Integer lastId);
	
	List<OrcamentoDTO> findOrcamentoByLastDataCadastro(String data);
	
	List<OrcamentoDTO> findOrcamentoByDataEntrada(Date data);
	
	List<OrcamentoDTO> findOrcamentoByNrOrcs(List<Integer> nrOrcs);

	List<Integer> findNrOrcsPorData(Date data);	
	
	List<String> buscarSubstanciasDoOrcamento(Integer numOrc, Integer codFilial, String serie);
	
	boolean isOrcamentoAprovado(Integer numOrc, Integer codFilial, String serie);

}
