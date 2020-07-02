package corp.gruposfa.novo.consinco.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAddCategoriaDTO {

	private Boolean r;

	private ResponseCategoriaResultDTO data;

	public Boolean getR() {
		return r;
	}

	public void setR(Boolean r) {
		this.r = r;
	}

	public ResponseCategoriaResultDTO getData() {
		return data;
	}

	public void setData(ResponseCategoriaResultDTO data) {
		this.data = data;
	}

}
