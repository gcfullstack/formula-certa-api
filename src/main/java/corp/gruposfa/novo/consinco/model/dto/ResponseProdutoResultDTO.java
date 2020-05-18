package corp.gruposfa.novo.consinco.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProdutoResultDTO {
	
	private Integer totalCount;
	
	private List<ResponseProdutoDadoDTO> produtos;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<ResponseProdutoDadoDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ResponseProdutoDadoDTO> produtos) {
		this.produtos = produtos;
	}

}
