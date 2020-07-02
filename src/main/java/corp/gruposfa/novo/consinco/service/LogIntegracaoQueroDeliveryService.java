package corp.gruposfa.novo.consinco.service;

import java.util.List;

import corp.gruposfa.novo.model.LogIntegracaoQueroDelivery;
import corp.gruposfa.novo.model.dto.FiltroLogIntegracaoDTO;
import corp.gruposfa.novo.model.dto.ListagemLogIntegracaoDTO;
import corp.gruposfa.novo.model.dto.TipoLogDTO;

public interface LogIntegracaoQueroDeliveryService {
	
	void salvarLog(LogIntegracaoQueroDelivery log);
	
	List<ListagemLogIntegracaoDTO> buscarLogs(FiltroLogIntegracaoDTO filtro);
	
	List<TipoLogDTO> getListaTipoLogs();

}
