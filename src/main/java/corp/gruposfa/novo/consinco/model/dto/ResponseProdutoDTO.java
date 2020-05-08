package corp.gruposfa.novo.consinco.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProdutoDTO {

	private Boolean r;

	private ResponseProdutoDadoDTO data;
	
	public ResponseProdutoDTO() {
	}

	public ResponseProdutoDTO(Boolean r, ResponseProdutoDadoDTO data) {
		this.r = r;
		this.data = data;
	}

	public Boolean getR() {
		return r;
	}

	public void setR(Boolean r) {
		this.r = r;
	}

	public ResponseProdutoDadoDTO getData() {
		return data;
	}

	public void setData(ResponseProdutoDadoDTO data) {
		this.data = data;
	}

}
