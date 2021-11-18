package corp.formulacerta.integracao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta;
import corp.formulacerta.integracao.mirror.model.OrcTrail;
import corp.formulacerta.integracao.mirror.service.LogConsultaFormulaCertaService;
import corp.formulacerta.integracao.mirror.service.OrcTrailService;
import corp.formulacerta.integracao.model.dto.OrcamentoDTO;
import corp.formulacerta.integracao.service.IntegradorFormulaCertaService;
import corp.formulacerta.integracao.service.OrcamentoFormulaCertaService;
import corp.formulacerta.integracao.tray.model.dto.ProdutoCriadoDTO;
import corp.formulacerta.integracao.tray.model.dto.ProdutoDTO;
import corp.formulacerta.integracao.tray.service.ProdutoService;

@Service
public class IntegradorFormulaCertaServiceImpl implements IntegradorFormulaCertaService {
	
	private final LogConsultaFormulaCertaService logConsultaFormulaCertaService;
	
	private final OrcamentoFormulaCertaService orcamentoFormulaCertaService;
	
	private final OrcTrailService orcTrailService;
	
	private final ProdutoService produtoService;
	
	private static final Logger logger = Logger.getLogger(IntegradorFormulaCertaServiceImpl.class.getName());

	public IntegradorFormulaCertaServiceImpl(LogConsultaFormulaCertaService logConsultaFormulaCertaService,
			OrcamentoFormulaCertaService orcamentoFormulaCertaService, OrcTrailService orcTrailService,ProdutoService produtoService) {
		super();
		this.logConsultaFormulaCertaService = logConsultaFormulaCertaService;
		this.orcamentoFormulaCertaService = orcamentoFormulaCertaService;
		this.orcTrailService = orcTrailService;
		this.produtoService = produtoService;
	}

	@Override
	public void executarIntegracao() {
		Handler handlerObj = new ConsoleHandler();
		handlerObj.setLevel(Level.ALL);
		logger.addHandler(handlerObj);
		logger.setLevel(Level.ALL);
		logger.setUseParentHandlers(false);
		Date lastDataCadastroImported = logConsultaFormulaCertaService.findLastDataCadastroImported();
		List<OrcamentoDTO> orcamentos = orcamentoFormulaCertaService.findOrcamentoByLastDataCadastro(lastDataCadastroImported);
		logger.log(Level.FINE, "{0} Orçamentos para integrar", orcamentos.size() );
		if(!orcamentos.isEmpty()) {
			for (OrcamentoDTO orc : orcamentos) {
				List<String> substancias = orcamentoFormulaCertaService.buscarSubstanciasDoOrcamento(orc.getNumOrcamento(), orc.getCodFilial(), orc.getSerie());
				orc.setDescricaoCompleta(String.join(",", substancias));
				orc.setDescricaoSimples("Produto: " + (Integer.parseInt(orc.getSerie()) + 1));
				OrcTrail orcTray = new OrcTrail(orc);
				OrcTrail orcSaved = orcTrailService.saveEntity(orcTray);
				ProdutoCriadoDTO produtoIntegrado = produtoService.cadastrarProduto(new ProdutoDTO(orcTray));
				orcTrailService.updateIdProdutoTray(orcSaved.getId(), produtoIntegrado.getId());
			}
		}
		logConsultaFormulaCertaService.saveLog(getLogObject(orcamentos, lastDataCadastroImported));
	}
	
	/*@Override
	public void executarIntegracao2(Date data) {
		List<Integer> idsOrcamentosParaIntegrar = new ArrayList<>();
		List<Integer> orcamentosNaoIntegradosDaData = orcamentoFormulaCertaService.findNrOrcsPorData(data);
		
		if(orcamentosNaoIntegradosDaData.isEmpty()) {
			return;
		}
		
		List<Integer> orcamentosIntegradosDaData = orcTrailService.buscarNumOrcamentosPorData(data);
		
		if(orcamentosIntegradosDaData.isEmpty()) {
			idsOrcamentosParaIntegrar.addAll(orcamentosNaoIntegradosDaData);
		}else {
			idsOrcamentosParaIntegrar = orcamentosNaoIntegradosDaData.stream().filter(p -> !orcamentosIntegradosDaData.contains(p)).collect(Collectors.toList());
		}
		
		for (Integer integer : idsOrcamentosParaIntegrar) {
			System.out.println(integer+",");
		}
		
		List<OrcamentoDTO> orcamentos = orcamentoFormulaCertaService.findOrcamentoByNrOrcs(idsOrcamentosParaIntegrar);
		
		if(!orcamentos.isEmpty()) {
			for (OrcamentoDTO orc : orcamentos) {
				OrcTrail orcTray = new OrcTrail(orc);
				OrcTrail orcSaved = orcTrailService.saveEntity(orcTray);
				ProdutoCriadoDTO produtoIntegrado = produtoService.cadastrarProduto(new ProdutoDTO(orcTray));
				orcTrailService.updateIdProdutoTray(orcSaved.getId(), produtoIntegrado.getId());
			}
		}
		//logConsultaFormulaCertaService.saveLog(getLogObject(orcamentos, 000));
	}*/
	
	private LogConsultaFormulaCerta getLogObject(List<OrcamentoDTO> orcamentos, Date lastDataCadastro) {
		LogConsultaFormulaCerta log = new LogConsultaFormulaCerta();
		log.setDataExec(new Date());
		log.setRecordFound(!orcamentos.isEmpty());
		log.setLastDataCadastro(orcamentos.isEmpty() ? lastDataCadastro : orcamentos.get(0).getDataCadastro());
		return log;
	}

 

}
