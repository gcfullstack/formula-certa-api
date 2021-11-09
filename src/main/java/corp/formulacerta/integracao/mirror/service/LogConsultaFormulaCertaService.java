package corp.formulacerta.integracao.mirror.service;

import java.util.Date;

import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta;

public interface LogConsultaFormulaCertaService {

	Date findLastDataCadastroImported();
	
	void saveLog(LogConsultaFormulaCerta log);
	
}
