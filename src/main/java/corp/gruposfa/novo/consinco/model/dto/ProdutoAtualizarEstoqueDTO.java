package corp.gruposfa.novo.consinco.model.dto;

public class ProdutoAtualizarEstoqueDTO {
	
	private Integer quantidade;

	public ProdutoAtualizarEstoqueDTO(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
