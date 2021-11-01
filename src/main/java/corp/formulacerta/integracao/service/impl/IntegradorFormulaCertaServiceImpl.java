package corp.formulacerta.integracao.service.impl;

import java.util.Date;
import java.util.List;

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
		Integer lastIdImported = logConsultaFormulaCertaService.findLastIdImported();
		List<OrcamentoDTO> orcamentos = orcamentoFormulaCertaService.findOrcamentoBiggerThanCustomID(lastIdImported);
		if(!orcamentos.isEmpty()) {
			for (OrcamentoDTO orc : orcamentos) {
				OrcTrail orcTray = new OrcTrail(orc);
				OrcTrail orcSaved = orcTrailService.saveEntity(orcTray);
				ProdutoCriadoDTO produtoIntegrado = produtoService.cadastrarProduto(new ProdutoDTO(orcTray));
				orcTrailService.updateIdProdutoTray(orcSaved.getId(), produtoIntegrado.getId());
			}
		}
		logConsultaFormulaCertaService.saveLog(getLogObject(orcamentos, lastIdImported));
	}
	
	private LogConsultaFormulaCerta getLogObject(List<OrcamentoDTO> orcamentos, Integer lastIdImported) {
		LogConsultaFormulaCerta log = new LogConsultaFormulaCerta();
		log.setDataExec(new Date());
		log.setRecordFound(!orcamentos.isEmpty());
		log.setLastImportedId(orcamentos.isEmpty() ? lastIdImported : orcamentos.get(orcamentos.size()-1).getNumOrcamento());
		return log;
	}

 

}
