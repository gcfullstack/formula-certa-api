package corp.formulacerta.integracao.mirror.service;

import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta;

public interface LogConsultaFormulaCertaService {

	Integer findLastIdImported();
	
	void saveLog(LogConsultaFormulaCerta log);
	
}
