package corp.gruposfa.novo.consinco.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseCategoriaDadoDTO {
	
	private Integer totalCount;
	
	private List<ResponseCategoriaResultDTO> result;
	

	public ResponseCategoriaDadoDTO() {
	}

	public ResponseCategoriaDadoDTO(Integer totalCount, List<ResponseCategoriaResultDTO> result) {
		this.totalCount = totalCount;
		this.result = result;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<ResponseCategoriaResultDTO> getResult() {
		return result;
	}

	public void setResult(List<ResponseCategoriaResultDTO> result) {
		this.result = result;
	}
	
	

}
