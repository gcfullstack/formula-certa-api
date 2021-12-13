package corp.formulacerta.integracao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import corp.formulacerta.integracao.model.dto.OrcamentoDTO;
import corp.formulacerta.integracao.repository.OrcamentoFormulaCertaRepository;
import corp.formulacerta.integracao.service.OrcamentoFormulaCertaService;
import corp.formulacerta.integracao.utils.ConstantsUtils;
import corp.formulacerta.integracao.utils.MethodsUtils;

@Service
public class OrcamentoFormulaCertaServiceImpl implements OrcamentoFormulaCertaService {
	
	private final OrcamentoFormulaCertaRepository orcamentoFormulaCertaRepository;

	public OrcamentoFormulaCertaServiceImpl(OrcamentoFormulaCertaRepository orcamentoFormulaCertaRepository) {
		this.orcamentoFormulaCertaRepository = orcamentoFormulaCertaRepository;
	}

	@Override
	public List<OrcamentoDTO> findOrcamentoBiggerThanCustomID(Integer lastId) {
		return orcamentoFormulaCertaRepository.findOrcamentoBiggerThanCustomID(lastId).stream().map(orc -> new OrcamentoDTO(orc)).collect(Collectors.toList());
	}
	
	@Override
	public List<OrcamentoDTO> findOrcamentoByLastDataCadastro(String data) {
		return orcamentoFormulaCertaRepository.findOrcamentoByLastDataCadastro(data,64).stream().map(orc -> new OrcamentoDTO(orc)).collect(Collectors.toList());
	}
	
	@Override
	public List<OrcamentoDTO> findOrcamentoByDataEntrada(Date data) {
		return orcamentoFormulaCertaRepository.findOrcamentoByDataEntrada(MethodsUtils.formatarDataString(data, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD)).stream().map(orc -> new OrcamentoDTO(orc)).collect(Collectors.toList());
	}
	
	@Override
	public List<Integer> findNrOrcsPorData(Date data){
		return orcamentoFormulaCertaRepository.findNrOrcsPorData(MethodsUtils.formatarDataString(data, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD));
	}
	
	@Override
	public List<OrcamentoDTO> findOrcamentoByNrOrcs(List<Integer> nrOrcs) {
		return orcamentoFormulaCertaRepository.findOrcamentoByNrOrcs(nrOrcs).stream().map(orc -> new OrcamentoDTO(orc)).collect(Collectors.toList());
	}

	@Override
	public List<String> buscarSubstanciasDoOrcamento(Integer numOrc, Integer codFilial, String serie) {
		return orcamentoFormulaCertaRepository.buscarSubstanciasDoOrcamento(numOrc, codFilial, serie);
	}

	@Override
	public boolean isOrcamentoAprovado(Integer numOrc, Integer codFilial, String serie) {
		return orcamentoFormulaCertaRepository.buscarOrcamentoAprovado(numOrc, codFilial, serie) > 0;
	}

}