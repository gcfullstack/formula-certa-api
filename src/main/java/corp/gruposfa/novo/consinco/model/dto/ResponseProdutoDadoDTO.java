package corp.gruposfa.novo.consinco.model.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProdutoDadoDTO {

	private String _id;
	
	private String placeId;
	
	private String produtoCategoriaId;
	
	private String nome;
	
	private String descricao;
	
	private String status;
	
	private BigDecimal preco;
	
	private BigDecimal precoAntigo;

	private String codigoBarras;
	
	private String codigoInterno;
	
	private ProdutoEstoqueDTO produto;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getProdutoCategoriaId() {
		return produtoCategoriaId;
	}

	public void setProdutoCategoriaId(String produtoCategoriaId) {
		this.produtoCategoriaId = produtoCategoriaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ProdutoEstoqueDTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoEstoqueDTO produto) {
		this.produto = produto;
	}
}
