package corp.formulacerta.integracao.mirror.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta;
import corp.formulacerta.integracao.mirror.repository.LogConsultaFormulaCertaRepository;
import corp.formulacerta.integracao.mirror.service.LogConsultaFormulaCertaService;

@Service
public class LogConsultaFormulaCertaServiceImpl implements LogConsultaFormulaCertaService {

	private final LogConsultaFormulaCertaRepository logConsultaFormulaCertaRepository;

	public LogConsultaFormulaCertaServiceImpl(LogConsultaFormulaCertaRepository logConsultaFormulaCertaRepository) {
		this.logConsultaFormulaCertaRepository = logConsultaFormulaCertaRepository;
	}

	@Override
	public Date findLastDataCadastroImported() {
		try {
			return logConsultaFormulaCertaRepository.findTopByOrderByIdDesc2();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao buscar a ultima data de cadastro importada");
		}
	}

	@Override
	public void saveLog(LogConsultaFormulaCerta log) {
		logConsultaFormulaCertaRepository.saveAndFlush(log);
	}

}
