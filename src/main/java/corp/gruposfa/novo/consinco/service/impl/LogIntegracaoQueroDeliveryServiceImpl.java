package corp.gruposfa.novo.consinco.service.impl;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.service.LogIntegracaoQueroDeliveryService;
import corp.gruposfa.novo.model.LogIntegracaoQueroDelivery;
import corp.gruposfa.novo.repository.LogIntegracaoQueroDeliveryRepository;

@Service
public class LogIntegracaoQueroDeliveryServiceImpl implements LogIntegracaoQueroDeliveryService{

	private final LogIntegracaoQueroDeliveryRepository logIntegracaoQueroDeliveryRepository;

	public LogIntegracaoQueroDeliveryServiceImpl(LogIntegracaoQueroDeliveryRepository logIntegracaoQueroDeliveryRepository) {
		this.logIntegracaoQueroDeliveryRepository = logIntegracaoQueroDeliveryRepository;
	}
	
	public void salvarLog(LogIntegracaoQueroDelivery log) {
		logIntegracaoQueroDeliveryRepository.saveAndFlush(log);
	}
	
}
