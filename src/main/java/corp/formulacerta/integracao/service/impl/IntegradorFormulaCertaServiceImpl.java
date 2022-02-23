package corp.formulacerta.integracao.service.impl;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.mirror.model.OrcTrail;
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
	
	private final OrcamentoFormulaCertaService orcamentoFormulaCertaService;
	
	private final ProdutoService produtoService;
	
	private final OrcamentoN8NService orcamentoN8NService;
	
	private final LogOrcamentoN8NService logOrcamentoN8NService;

	private final static long QTD_DIAS_BUSCA_ORCAMENTO = 2;

	public IntegradorFormulaCertaServiceImpl(
			OrcamentoFormulaCertaService orcamentoFormulaCertaService,ProdutoService produtoService, OrcamentoN8NService orcamentoN8NService, LogOrcamentoN8NService logOrcamentoN8NService) {
		super();
		this.orcamentoFormulaCertaService = orcamentoFormulaCertaService;
		this.produtoService = produtoService;
		this.orcamentoN8NService = orcamentoN8NService;
		this.logOrcamentoN8NService = logOrcamentoN8NService;
	}

	/**
	 * Etapas do processo de importação e integração de orçamentos
	 * 
	 * 1 - É realizada uma consulta no banco do mongo, para buscar todos os orçamentos que ainda não foram integrados na Tray.
	 * As condições da consulta é que o registro tenha o campo "idtray" igual a nulo e que a data de cadastro seja maior que a data atual subtraída de 3 dias.
	 * Esta regra foi criada para que exista um limite de data para que o sistema tente integrar os orçamentos retardatários	 * 
	 * Se algum registro for encontrado, ele será validado no banco do formula certa (firebird), afim de saber se esse orçamento ja foi aprovado.
	 * Caso positivo, o orçamento sera integrado na Tray e o campo  "idtray" sera preenchido e atualizado no banco.
	 * Caso negativo, não possui ação.
	 * 
	 * 2 - É realizada uma busca no mongo para buscar o ultimo log de execução salvo, pois ele contém a data de cadastro do ultimo orçamento importado.
	 * Essa, servirá de referência para buscar os proximos orçamentos.
	 * 
	 * 3 - É realizada uma validação para verificar se a data de cadastro do orçamento mais recente cadastrado no banco é menor do que 
	 * um determinado numero de dias, o qual é utilizado para definir um limite maximo de dias que o sistema ira permitir importar novos orçamentos.
	 * Essa regra foi criada para o caso do sistema ficar muitos dias sem executar e quando for executar, acontecer de existir milhares de registros para serem
	 * importados de uma vez.
	 * Caso a diferença de dias seja maior do que a data limite, o sistema irá criar uma data padrao que é equivalente ao dia atual às 00:00:00hrs.
	 * 
	 * 4 - É realizada uma busca no banco do formula certa para verificar se existem registros(orçamentos) que possuam o campo data de cadastro
	 * maior do que a data de cadastro (lastDataCadastro campo do banco) do registro encontrado na etapa 3
	 * Os registros que forem encontrados serão integrados na tray, caso algum dos produtos que constituem o orçamento, esteja aprovado.
	 * Essa regra foi criada desta forma, pois o banco do formula certa nao informa se um determinado produto do orçamento esta aprovado ou não,
	 * ele possui apenas a quantidade de produtos aprovados.
	 * 
	 * 5 - No final um log é criado e salvo no banco, com a data de cadastro do orçamento mais recente encontrado. Para que ela sirva de referencia
	 *  para buscar os proximos orçamentos
	 */
	@Override
	public void executarIntegracao() {
		/**
		 * Passo 1
		 */
		List<OrcamentoN8N> orcamentosNaoIntegrados = orcamentoN8NService.buscarOrcamentoByDateLessThanCurrentDate(getCurrentDateMinusDays(3));
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
		
		/**
		 * Passo 2
		 */
		String lastDataCadastroStr = logOrcamentoN8NService.buscarUltimoRegistroLog().getLastDataCadastro();
		lastDataCadastroStr = Instant.parse(lastDataCadastroStr).atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern(ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS));
		Date lastDataCadastroImported = MethodsUtils.formatarStringData(lastDataCadastroStr, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		
		/**
		 * Passo 3
		 */
		
		long diffInMillies = Math.abs(new Date().getTime() - lastDataCadastroImported.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		if (diff >= QTD_DIAS_BUSCA_ORCAMENTO) {
			lastDataCadastroImported = MethodsUtils.definirPrimeiraHoraDoDia(new Date());
			lastDataCadastroStr = MethodsUtils.formatarDataString(lastDataCadastroImported, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		}
		System.out.println("Ultima data de importacao: " + lastDataCadastroImported);
		
		
		/**
		 * Passo 4
		 */
		List<OrcamentoDTO> orcamentos = orcamentoFormulaCertaService.findOrcamentoByLastDataCadastro(lastDataCadastroStr);
		System.out.println("Orcamentos encontrados: " + orcamentos.size());
		if(!orcamentos.isEmpty()) {
			for (OrcamentoDTO orc : orcamentos) {
					List<String> substancias = orcamentoFormulaCertaService.buscarSubstanciasDoOrcamento(orc.getNumOrcamento(), orc.getCodFilial(), orc.getSerie());
					orc.setDescricaoCompleta(String.join(",", substancias));
					orc.setDescricaoSimples("Manipulado: " + orc.getNumOrcamento() + " - " + (Integer.parseInt(orc.getSerie()) + 1) + getFormaFarmaceuticaValidated(orc.getFormaFarmaceutica()));
					OrcTrail orcTray = new OrcTrail(orc);
					orcamentoN8NService.salvarOrcamento(new OrcamentoN8N(orcTray), OrcamentoN8N.class);
					if(orc.getQtAprov() > 0) {
						ProdutoCriadoDTO produtoIntegrado = produtoService.cadastrarProduto(new ProdutoDTO(orcTray));
						orcamentoN8NService.atualizarOrcamento(new OrcamentoUpdateN8N(orcTray.getId(), produtoIntegrado.getId()), OrcamentoUpdateN8N.class);
					}
			}
		}
		
		/**
		 * Passo 5
		 */
		logOrcamentoN8NService.salvarLogOrcamento(getLogObject(orcamentos, lastDataCadastroImported));
	}
	
	private String getFormaFarmaceuticaValidated(String formaFarmaceutica) {
		return formaFarmaceutica != null ? " (" + formaFarmaceutica + ")" : "";
	}

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
