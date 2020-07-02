package corp.gruposfa.novo.consinco.model.dto;

import java.math.BigDecimal;

public class ProdutoCadastroQueroDeliveryDTO {
	
	private String nome;
	
	private String categoriaId;

	private String descricao;
	
	private String status;
	
	private BigDecimal preco;
	
	private BigDecimal precoAntigo;

	private String codigoBarras;
	
	private String codigoInterno;
	
	private Boolean isPesavel;
	
	private Boolean isPromocao;
	
	private Boolean isSazonal;
	
	public ProdutoCadastroQueroDeliveryDTO() {
	}

	public ProdutoCadastroQueroDeliveryDTO(String nome, String categoriaId, String descricao, String status,
			BigDecimal preco, BigDecimal precoAntigo, String codigoBarras, String codigoInterno, Boolean isPesavel,
			Boolean isPromocao, Boolean isSazonal) {
		this.nome = nome;
		this.categoriaId = categoriaId;
		this.descricao = descricao;
		this.status = status;
		this.preco = preco;
		this.precoAntigo = precoAntigo;
		this.codigoBarras = codigoBarras;
		this.codigoInterno = codigoInterno;
		this.isPesavel = isPesavel;
		this.isPromocao = isPromocao;
		this.isSazonal = isSazonal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}

	public void setCodigoInterno(String codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	public Boolean getIsPesavel() {
		return isPesavel;
	}

	public void setIsPesavel(Boolean isPesavel) {
		this.isPesavel = isPesavel;
	}

	public Boolean getIsPromocao() {
		return isPromocao;
	}

	public void setIsPromocao(Boolean isPromocao) {
		this.isPromocao = isPromocao;
	}

	public Boolean getIsSazonal() {
		return isSazonal;
	}

	public void setIsSazonal(Boolean isSazonal) {
		this.isSazonal = isSazonal;
	}

}
