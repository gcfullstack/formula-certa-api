package corp.gruposfa.novo.consinco.model.dto;

public class ProdutoAlterarControlaEstoqueDTO {

	private Boolean controlaEstoque;

	public ProdutoAlterarControlaEstoqueDTO() {
	}

	public ProdutoAlterarControlaEstoqueDTO(Boolean controlaEstoque) {
		this.controlaEstoque = controlaEstoque;
	}

	public Boolean getControlaEstoque() {
		return controlaEstoque;
	}

	public void setControlaEstoque(Boolean controlaEstoque) {
		this.controlaEstoque = controlaEstoque;
	}

	
}
