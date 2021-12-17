package corp.formulacerta.integracao.n8n.service;

import java.util.Date;
import java.util.List;

import corp.formulacerta.integracao.n8n.model.dto.OrcamentoN8N;

public interface OrcamentoN8NService {

	public OrcamentoN8N[] buscarOrcamento(Integer page);
	
	public Object salvarOrcamento(Object dto, Class clazz);
	
	public Object atualizarOrcamento(Object dto, Class clazz);
	
	public List<OrcamentoN8N> buscarOrcamentoByDateLessThanCurrentDate(Date data);

	
}
