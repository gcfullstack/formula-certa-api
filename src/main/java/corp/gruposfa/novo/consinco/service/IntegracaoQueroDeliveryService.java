package corp.gruposfa.novo.consinco.service;

import corp.gruposfa.novo.consinco.model.dto.ParametrizacaoAmbienteDTO;

public interface IntegracaoQueroDeliveryService {
	
	void integrarDadosQueroDelivery(ParametrizacaoAmbienteDTO param);
	
	void executarJobIntegracao();
}
