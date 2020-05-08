package corp.gruposfa.novo.consinco.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCategoriaResultDTO {

	private String _id;
	private String nome;
	private Boolean isAtivo;
	
	

	public ResponseCategoriaResultDTO() {
	}

	public ResponseCategoriaResultDTO(String _id, String nome, Boolean isAtivo) {
		this._id = _id;
		this.nome = nome;
		this.isAtivo = isAtivo;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

}
