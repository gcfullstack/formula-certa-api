package corp.formulacerta.integracao.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta;
import corp.formulacerta.integracao.mirror.model.OrcTrail;
import corp.formulacerta.integracao.mirror.service.LogConsultaFormulaCertaService;
import corp.formulacerta.integracao.mirror.service.OrcTrailService;
import corp.formulacerta.integracao.model.dto.OrcamentoDTO;
import corp.formulacerta.integracao.n8n.model.dto.LogConsultaFormulaCertaDTO;
import corp.formulacerta.integracao.n8n.model.dto.OrcamentoN8N;
import corp.formulacerta.integracao.n8n.model.dto.OrcamentoUpdateN8N;
import corp.formulacerta.integracao.n8n.service.LogOrcamentoN8NService;
import corp.formulacerta.integracao.n8n.service.OrcamentoN8NService;
import corp.formulacerta.integracao.service.IntegradorFormulaCertaService;
import corp.formulacerta.integracao.service.OrcamentoFormulaCertaService;
import corp.formulacerta.integracao.tray.model.dto.ProdutoCriadoDTO;
import corp.formulacerta.integracao.tray.model.dto.ProdutoDTO;
import corp.formulacerta.integracao.tray.service.ProdutoService;
import corp.formulacerta.integracao.utils.ConstantsUtils;
import corp.formulacerta.integracao.utils.MethodsUtils;

@Service
public class IntegradorFormulaCertaServiceImpl implements IntegradorFormulaCertaService {
	
	private final LogConsultaFormulaCertaService logConsultaFormulaCertaService;
	
	private final OrcamentoFormulaCertaService orcamentoFormulaCertaService;
	
	private final OrcTrailService orcTrailService;
	
	private final ProdutoService produtoService;
	
	private final OrcamentoN8NService orcamentoN8NService;
	
	private final LogOrcamentoN8NService logOrcamentoN8NService;

	private static final Logger logger = Logger.getLogger(IntegradorFormulaCertaServiceImpl.class.getName());
	

	public IntegradorFormulaCertaServiceImpl(LogConsultaFormulaCertaService logConsultaFormulaCertaService,
			OrcamentoFormulaCertaService orcamentoFormulaCertaService, OrcTrailService orcTrailService,ProdutoService produtoService, OrcamentoN8NService orcamentoN8NService, LogOrcamentoN8NService logOrcamentoN8NService) {
		super();
		this.logConsultaFormulaCertaService = logConsultaFormulaCertaService;
		this.orcamentoFormulaCertaService = orcamentoFormulaCertaService;
		this.orcTrailService = orcTrailService;
		this.produtoService = produtoService;
		this.orcamentoN8NService = orcamentoN8NService;
		this.logOrcamentoN8NService = logOrcamentoN8NService;
	}

	/**
	 * verificar:
	 * - se o produto integrou na tray com todos os campos, pois alteramos o construtor do new ProdutoDTO()
	 * - testar tudo
	 */
	@Override
	public void executarIntegracao() {
		// buscar no mongo todo os orcamento que estao com idtray igual a nulo e a data de datadastro
		// seja maior que a data de hoje menos 3 dias (d-3)
		Calendar c = Calendar.getInstance();
		c.set(2021, 6, 13, 00, 00);
		List<OrcamentoN8N> orcamentosNaoIntegrados = orcamentoN8NService.buscarOrcamentoByDateLessThanCurrentDate(c.getTime());
		if(!orcamentosNaoIntegrados.isEmpty()) {
			for (OrcamentoN8N orcNaoIntegrado : orcamentosNaoIntegrados) {
				// buscar na attive, os orcamentos que tenham numorc, filial e serie equivalentes ao registros das linhas de cima
				// e que estejam qtaprov = 1
				if(orcamentoFormulaCertaService.isOrcamentoAprovado(orcNaoIntegrado.getNumOrcamento(), orcNaoIntegrado.getCodFilial(), orcNaoIntegrado.getSerie())) {
					ProdutoCriadoDTO produtoIntegrado = produtoService.cadastrarProduto(new ProdutoDTO(orcNaoIntegrado));
					orcamentoN8NService.atualizarOrcamento(new OrcamentoUpdateN8N(getIdOrcamentoConcat(orcNaoIntegrado.getNumOrcamento(), orcNaoIntegrado.getSerie(), orcNaoIntegrado.getCodFilial()), produtoIntegrado.getId()), OrcamentoUpdateN8N.class);
				}
			}
		}
		
		String lastDataCadastroStr = logOrcamentoN8NService.buscarUltimoRegistroLog().getLastDataCadastro();
		
		Date lastDataCadastroImported = MethodsUtils.formatarStringData(lastDataCadastroStr, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_TT);
		List<OrcamentoDTO> orcamentos = orcamentoFormulaCertaService.findOrcamentoByLastDataCadastro(lastDataCadastroStr);
		if(!orcamentos.isEmpty()) {
			for (OrcamentoDTO orc : orcamentos) {
					List<String> substancias = orcamentoFormulaCertaService.buscarSubstanciasDoOrcamento(orc.getNumOrcamento(), orc.getCodFilial(), orc.getSerie());
					orc.setDescricaoCompleta(String.join(",", substancias));
					orc.setDescricaoSimples("Produto: " + (Integer.parseInt(orc.getSerie()) + 1));
					OrcTrail orcTray = new OrcTrail(orc);
					orcamentoN8NService.salvarOrcamento(new OrcamentoN8N(orcTray), OrcamentoN8N.class);
					//OrcTrail orcSaved = orcTrailService.saveEntity(orcTray);
					if(orc.getQtAprov() > 0) {
						ProdutoCriadoDTO produtoIntegrado = produtoService.cadastrarProduto(new ProdutoDTO(orcTray));
						orcamentoN8NService.atualizarOrcamento(new OrcamentoUpdateN8N(orcTray.getId(), produtoIntegrado.getId()), OrcamentoUpdateN8N.class);
					}
					//orcTrailService.updateIdProdutoTray(orcTray.getI, produtoIntegrado.getId());
			}
		}
		System.out.println("finish");
		logOrcamentoN8NService.salvarLogOrcamento(getLogObject(orcamentos, lastDataCadastroImported));
	//	logConsultaFormulaCertaService.saveLog(getLogObject(orcamentos, lastDataCadastroImported));
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
	
	private LogConsultaFormulaCertaDTO getLogObject(List<OrcamentoDTO> orcamentos, Date lastDataCadastro) {
		LogConsultaFormulaCertaDTO log = new LogConsultaFormulaCertaDTO();
		log.setDataExec(MethodsUtils.formatarDataString(new Date(), ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		log.setRecordFound(!orcamentos.isEmpty());
		log.setLastDataCadastro(orcamentos.isEmpty() ? MethodsUtils.formatarDataString(lastDataCadastro, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS) : MethodsUtils.formatarDataString(orcamentos.get(orcamentos.size() - 1).getDataEntrada(), ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		return log;
	}
	
	private Date getCurrentDateMinusDays(Integer amountDays) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, (amountDays * -1));
		return cal.getTime();
	}

	private String getIdOrcamentoConcat(Integer numOrc, String serie, Integer codFilial) {
		if(numOrc != null && serie != null && codFilial != null) {
			return numOrc + "-" + serie + "-" + codFilial;
		}
		throw new RuntimeException("Erro ao executar getIdOrcamentoConcat");
	}
 

}
