package corp.formulacerta.integracao.n8n.service;

import corp.formulacerta.integracao.n8n.model.dto.LogConsultaFormulaCertaDTO;

public interface LogOrcamentoN8NService {

	public LogConsultaFormulaCertaDTO salvarLogOrcamento(LogConsultaFormulaCertaDTO dto);
	
	public LogConsultaFormulaCertaDTO buscarUltimoRegistroLog();

	
}
