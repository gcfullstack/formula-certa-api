package corp.gruposfa.novo.consinco.model.dto;

import java.math.BigDecimal;

public class ProdutoAtualizarPrecoDTO {
	
	private BigDecimal preco;
	
	private BigDecimal precoAntigo;
	
	public ProdutoAtualizarPrecoDTO() {
	}

	public ProdutoAtualizarPrecoDTO(BigDecimal preco, BigDecimal precoAntigo) {
		this.preco = preco;
		this.precoAntigo = precoAntigo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPrecoAntigo() {
		return precoAntigo;
	}

	public void setPrecoAntigo(BigDecimal precoAntigo) {
		this.precoAntigo = precoAntigo;
	}

}
