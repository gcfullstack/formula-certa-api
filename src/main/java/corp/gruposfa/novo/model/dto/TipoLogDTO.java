package corp.gruposfa.novo.model.dto;

public class TipoLogDTO {

	private String value;

	private String descricao;
	
	public TipoLogDTO() {
	}

	public TipoLogDTO(String value, String descricao) {
		this.value = value;
		this.descricao = descricao;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
