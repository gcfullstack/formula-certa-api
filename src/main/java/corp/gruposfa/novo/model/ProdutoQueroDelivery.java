package corp.gruposfa.novo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NVO_PRODUTO_QUERO_DELIVERY")
public class ProdutoQueroDelivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@Basic(optional = false)
	private Integer id;
	
	@Column(name = "COD_LOJA")
	private Integer codLoja;
	
	@Column(name = "COD_PRODUTO")
	private Integer codProduto;

	@Column(name = "NOME_PRODUTO")
	private String nomeProduto;

	@Column(name = "COD_BARRA")
	private String codBarra;
	
	@Column(name = "PRECO_VAREJO")
	private BigDecimal precoVarejo;

	@Column(name = "QTD_ESTOQUE")
	private Integer qtdEstoque;
	
	@Column(name = "DT_ALTERACAO_ESTOQUE")
	private Date dataAlteracaoEstoque;
	
	public ProdutoQueroDelivery() {
	}
	
	

	public ProdutoQueroDelivery(Integer codLoja, Integer codProduto,
			String nomeProduto, String codBarra, BigDecimal precoVarejo, Integer qtdEstoque, Date dataAlteracaoEstoque) {
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
