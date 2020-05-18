package corp.gruposfa.novo.consinco.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseTodosProdutosDTO {

	private Boolean r;

	private ResponseProdutoResultDTO data;
	
	public ResponseTodosProdutosDTO() {
	}

	public ResponseTodosProdutosDTO(Boolean r, ResponseProdutoResultDTO data) {
		this.r = r;
		this.data = data;
	}

	public Boolean getR() {
		return r;
	}

	public void setR(Boolean r) {
		this.r = r;
	}

	public ResponseProdutoResultDTO getData() {
		return data;
	}

	public void setData(ResponseProdutoResultDTO data) {
		this.data = data;
	}

}
