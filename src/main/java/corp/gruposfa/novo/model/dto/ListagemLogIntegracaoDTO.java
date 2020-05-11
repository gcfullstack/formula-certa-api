package corp.gruposfa.novo.model.dto;

import java.util.Date;

public class ListagemLogIntegracaoDTO {

	private Date data;

	private String tipoLog;

	private String historico;

	private String codBarras;

	private String nomeCategoria;
	
	public ListagemLogIntegracaoDTO() {
	}

	public ListagemLogIntegracaoDTO(Date data, String tipoLog, String historico, String codBarras, String nomeCategoria) {
		this.data = data;
		this.tipoLog = tipoLog;
		this.historico = historico;
		this.codBarras = codBarras;
		this.nomeCategoria = nomeCategoria;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipoLog() {
		return tipoLog;
	}

	public void setTipoLog(String tipoLog) {
		this.tipoLog = tipoLog;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

}
