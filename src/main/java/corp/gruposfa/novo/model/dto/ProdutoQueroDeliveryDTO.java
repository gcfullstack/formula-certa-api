package corp.gruposfa.novo.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProdutoQueroDeliveryDTO {
	
	private Integer id;
	
	private Integer codLoja;
	
	private Integer codProduto;

	private String nomeProduto;

	private String codBarra;
	
	private BigDecimal precoVarejo;

	private Integer qtdEstoque;
	
	private Date dataAlteracaoEstoque;
	
	
	public ProdutoQueroDeliveryDTO() {
	}

	public ProdutoQueroDeliveryDTO(Integer id, Integer codLoja,  Integer codProduto,
			String nomeProduto, String codBarra, BigDecimal precoVarejo, Integer qtdEstoque, Date dataAlteracaoEstoque) {
		this.id = id;
		this.codLoja = codLoja;
		this.codProduto = codProduto;
		this.nomeProduto = nomeProduto;
		this.codBarra = codBarra;
		this.precoVarejo = precoVarejo;
		this.qtdEstoque = qtdEstoque;
		this.dataAlteracaoEstoque = dataAlteracaoEstoque;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodLoja() {
		return codLoja;
	}

	public void setCodLoja(Integer codLoja) {
		this.codLoja = codLoja;
	}

	public Integer getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Integer codProduto) {
		this.codProduto = codProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public BigDecimal getPrecoVarejo() {
		return precoVarejo;
	}

	public void setPrecoVarejo(BigDecimal precoVarejo) {
		this.precoVarejo = precoVarejo;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Date getDataAlteracaoEstoque() {
		return dataAlteracaoEstoque;
	}

	public void setDataAlteracaoEstoque(Date dataAlteracaoEstoque) {
		this.dataAlteracaoEstoque = dataAlteracaoEstoque;
	}

}
