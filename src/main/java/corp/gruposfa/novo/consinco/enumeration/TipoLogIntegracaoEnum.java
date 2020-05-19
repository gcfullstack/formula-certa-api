package corp.gruposfa.novo.consinco.enumeration;

public enum TipoLogIntegracaoEnum {

	ADICIONAR_CATEGORIA("Adicionar categoria"),
	INATIVAR_CATEGORIA("Inativar categoria"),
	EXCLUIR_CATEGORIA("Excluir categoria"),
	ADICIONAR_PRODUTO("Adicionar produto"),
	EXCLUIR_PRODUTO("Excluir produto"),
	ATUALIZAR_ESTOQUE_PRODUTO("Atualizar estoque"),
	ATUALIZAR_PRECO_PRODUTO("Atualizar preço"),
	ATUALIZAR_STATUS_PRODUTO("Atualizar status"),
	ATUALIZAR_DESCRICAO_PRODUTO("Atualizar descrição"),
	PRODUTO_SEM_CATEGORIA("Produto sem categoria"),
	PRODUTO_SEM_COD_BARRAS("Produto sem cód. de barras"),
	PRODUTO_PRECO_ZERADO("Produto preço zerado"),
	PRODUTO_NAO_ADICIONADO("Produto não foi adicionado");
	
	private String descricao;

	private TipoLogIntegracaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setD(String descricao) {
		this.descricao = descricao;
	}
	
}
