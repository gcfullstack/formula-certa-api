package corp.gruposfa.novo.model.dto;

import java.util.Date;

public class FiltroLogIntegracaoDTO {

	private Date data;
	
	private String horaInicial;
	
	private String horaFinal;

	private String tipoLog;

	private String codBarras;

	private String nomeCategoria;
	
	private String dataFormatada;

	public FiltroLogIntegracaoDTO() {
	}

	public FiltroLogIntegracaoDTO(Date data, String tipoLog, String codBarras, String nomeCategoria) {
		this.data = data;
		this.tipoLog = tipoLog;
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

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	
}
