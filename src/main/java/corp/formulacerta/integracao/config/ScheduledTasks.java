package corp.formulacerta.integracao.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import corp.formulacerta.integracao.repository.OrcamentoFormulaCertaRepository;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	private final OrcamentoFormulaCertaRepository repository;
	
	private Integer count = 0;
	
	public ScheduledTasks(OrcamentoFormulaCertaRepository repository) {
		super();
		this.repository = repository;
	}

	//@Scheduled(fixedRate = 50000)
	public void reportCurrentTime() {
	//	repository.teste();
	}

}