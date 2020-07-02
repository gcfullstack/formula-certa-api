package corp.gruposfa.novo.consinco.service.impl;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.enumeration.TipoLogIntegracaoEnum;
import corp.gruposfa.novo.consinco.service.LogIntegracaoQueroDeliveryService;
import corp.gruposfa.novo.model.LogIntegracaoQueroDelivery;
import corp.gruposfa.novo.model.dto.FiltroLogIntegracaoDTO;
import corp.gruposfa.novo.model.dto.ListagemLogIntegracaoDTO;
import corp.gruposfa.novo.model.dto.TipoLogDTO;
import corp.gruposfa.novo.repository.LogIntegracaoQueroDeliveryDynamicRepository;
import corp.gruposfa.novo.repository.LogIntegracaoQueroDeliveryRepository;
import corp.gruposfa.novo.utils.MethodsUtils;

@Service
public class LogIntegracaoQueroDeliveryServiceImpl implements LogIntegracaoQueroDeliveryService{

	private final LogIntegracaoQueroDeliveryRepository logIntegracaoQueroDeliveryRepository;
	
	private final LogIntegracaoQueroDeliveryDynamicRepository logIntegracaoQueroDeliveryDynamicRepository;

	public LogIntegracaoQueroDeliveryServiceImpl(LogIntegracaoQueroDeliveryRepository logIntegracaoQueroDeliveryRepository,LogIntegracaoQueroDeliveryDynamicRepository logIntegracaoQueroDeliveryDynamicRepository) {
		this.logIntegracaoQueroDeliveryRepository = logIntegracaoQueroDeliveryRepository;
		this.logIntegracaoQueroDeliveryDynamicRepository = logIntegracaoQueroDeliveryDynamicRepository;
	}
	
	public void salvarLog(LogIntegracaoQueroDelivery log) {
		logIntegracaoQueroDeliveryRepository.saveAndFlush(log);
	}
	
	public List<ListagemLogIntegracaoDTO> buscarLogs(FiltroLogIntegracaoDTO filtro){
		filtro.setDataFormatada(MethodsUtils.formatarDataString(filtro.getData(), "yyyy-MM-dd"));
		if(filtro.getHoraFinal() != null && filtro.getHoraFinal().length() > 1) {
			filtro.setHoraInicial(filtro.getDataFormatada() + " " + filtro.getHoraInicial());
			filtro.setHoraFinal(filtro.getDataFormatada()  + " " +  filtro.getHoraFinal());
		}
		return logIntegracaoQueroDeliveryDynamicRepository.buscarLogs(filtro);
	}
	
	public List<TipoLogDTO> getListaTipoLogs(){
		List<TipoLogDTO> list = new ArrayList<>();
		ArrayList<TipoLogIntegracaoEnum> listEnum = new ArrayList<TipoLogIntegracaoEnum>(EnumSet.allOf(TipoLogIntegracaoEnum.class));
		for (TipoLogIntegracaoEnum t : listEnum) {
			list.add(new TipoLogDTO(t.toString(), t.getDescricao()));
		}
		return list;
	}
}
