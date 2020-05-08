package corp.gruposfa.novo.consinco.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCategoriaDTO {

	private Boolean r;

	private ResponseCategoriaDadoDTO data;
	
	public ResponseCategoriaDTO() {
	}

	public ResponseCategoriaDTO(Boolean r, ResponseCategoriaDadoDTO data) {
		this.r = r;
		this.data = data;
	}

	public Boolean getR() {
		return r;
	}

	public void setR(Boolean r) {
		this.r = r;
	}

	public ResponseCategoriaDadoDTO getData() {
		return data;
	}

	public void setData(ResponseCategoriaDadoDTO data) {
		this.data = data;
	}

}
