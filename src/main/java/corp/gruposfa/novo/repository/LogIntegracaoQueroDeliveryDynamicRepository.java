package corp.gruposfa.novo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import corp.gruposfa.novo.consinco.enumeration.TipoLogIntegracaoEnum;
import corp.gruposfa.novo.model.dto.FiltroLogIntegracaoDTO;
import corp.gruposfa.novo.model.dto.ListagemLogIntegracaoDTO;

@Service
public class LogIntegracaoQueroDeliveryDynamicRepository {

	/**
	 * A utilização da anotação persistence context,
	 * serve para que o entitymanager seja gerenciado
	 * pelo contexto do spring, não havendo a necessidade
	 * de controlar as transacoes, como por exemplo fechar
	 * o entity manager ao final da execucao do método
	 */
	@PersistenceContext(unitName = "novo")
	private  EntityManager emNovoAtacadao;
	
	@SuppressWarnings("unchecked")
	public List<ListagemLogIntegracaoDTO> buscarLogs(FiltroLogIntegracaoDTO filtro){
		List<ListagemLogIntegracaoDTO> lista = new ArrayList<>();
		try {
			StringBuilder consulta = new StringBuilder();
	
			consulta.append(" SELECT DATA, TIPO_LOG,NOME_PRODUTO, COD_BARRAS,NOME_CATEGORIA,HISTORICO FROM NVO_LOG_INTEGRACAO_QUERO_DELIVERY WHERE 1 = 1");
			
			if(filtro.getHoraFinal() != null) {
				consulta.append(" AND DATA BETWEEN :dataInicial AND :dataFinal");
			}else {
				consulta.append(" AND DATA BETWEEN :dataInicial and :dataFinal");
			}
			
			if(filtro.getCodBarras() != null && !filtro.getCodBarras().isEmpty()) {
				consulta.append(" AND COD_BARRAS = :codBarras");
			}
			
			if(filtro.getNomeCategoria() != null && !filtro.getNomeCategoria().isEmpty()) {
				consulta.append(" AND NOME_CATEGORIA = :nomeCategoria");
			}
			
			if(filtro.getTipoLog() != null) {
				consulta.append(" AND TIPO_LOG = :tipoLog");
			}
			
			Query query = emNovoAtacadao.createNativeQuery(consulta.toString());
			
			if(filtro.getHoraFinal() != null) {
				query.setParameter("dataInicial", filtro.getHoraInicial());
				query.setParameter("dataFinal", filtro.getHoraFinal());
			}else {
				query.setParameter("dataInicial", filtro.getDataFormatada() + " 00:00:00");
				query.setParameter("dataFinal", filtro.getDataFormatada() + " 23:59:59");
			}
			
			if(filtro.getCodBarras() != null) {
				query.setParameter("codBarras", filtro.getCodBarras());
			}
			
			if(filtro.getNomeCategoria() != null) {
				query.setParameter("nomeCategoria", filtro.getNomeCategoria());
			}
			
			if(filtro.getTipoLog() != null) {
				query.setParameter("tipoLog", filtro.getTipoLog());
			}
				
			List<Object> listObj = query.getResultList();
			for(int i =0; i<listObj.size(); i++) {
				Object[] result = (Object[]) listObj.get(i);
				ListagemLogIntegracaoDTO modelo = new ListagemLogIntegracaoDTO();
				modelo.setData((Date) result[0]);
				modelo.setTipoLog(TipoLogIntegracaoEnum.valueOf((String) result[1]).getDescricao());
				modelo.setNomeProduto((String) result[2]);
				modelo.setCodBarras((String) result[3]);
				modelo.setNomeCategoria((String) result[4]);
				modelo.setHistorico((String) result[5]);
				lista.add(modelo);
			}
			
		}catch(Exception e) {
		}
		return lista;
	}
	
	
}
