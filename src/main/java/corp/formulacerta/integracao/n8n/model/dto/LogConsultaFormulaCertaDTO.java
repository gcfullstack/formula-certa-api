package corp.formulacerta.integracao.n8n.model.dto;

import java.io.Serializable;
import java.util.Date;

public class LogConsultaFormulaCertaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String _id;

	private Integer id;

	private String dataExec;

	private Boolean recordFound;

	private String lastDataCadastro;
	
	

	public LogConsultaFormulaCertaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LogConsultaFormulaCertaDTO(String _id, Integer id, String dataExec, Boolean recordFound,
			String lastDataCadastro) {
		super();
		this._id = _id;
		this.id = id;
		this.dataExec = dataExec;
		this.recordFound = recordFound;
		this.lastDataCadastro = lastDataCadastro;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataExec() {
		return dataExec;
	}

	public void setDataExec(String dataExec) {
		this.dataExec = dataExec;
	}

	public Boolean getRecordFound() {
		return recordFound;
	}

	public void setRecordFound(Boolean recordFound) {
		this.recordFound = recordFound;
	}

	public String getLastDataCadastro() {
		return lastDataCadastro;
	}

	public void setLastDataCadastro(String lastDataCadastro) {
		this.lastDataCadastro = lastDataCadastro;
	}
	
	

}
