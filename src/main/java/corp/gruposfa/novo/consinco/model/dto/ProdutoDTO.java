package corp.gruposfa.novo.consinco.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProdutoDTO {

	private Integer codLoja;
	private String nomeLoja;
	private Integer codSegmento;
	private String nomeSegmento;
	private Long codProduto;
	private String nomeProduto;
	private Integer codCategoria;
	private String nomeCategoria;
	private String codBarras;
	private BigDecimal precoVarejo;
	private BigDecimal precoVarejoPromocao;
	private String embVarejo;
	private Integer qntEmbVarejo;
	private BigDecimal precoAtacado;
	private BigDecimal precoAtacadoPromocao;
	private String embAtacado;
	private Integer qtdEmbAtacado;
	private Date dataUltimaAlteracaoPreco;
	private String statusVenda;
	private Integer embCompra;
	private Integer qtdEstoqueMenorEmb;
	private Integer qtdEstoqueEmbCompra;
	
	
	public ProdutoDTO() {
	}

	public ProdutoDTO(ProdutoInterfaceDTO interfaceDTO) {
		this.codLoja = interfaceDTO.getCod_Loja();
		this.nomeLoja = interfaceDTO.getNome_Loja();
		this.codSegmento = interfaceDTO.getCod_Segmento();
		this.nomeSegmento = interfaceDTO.getNome_Segmento();
		this.codProduto = interfaceDTO.getCod_Produto();
		this.nomeProduto = interfaceDTO.getNome_Produto();
		this.codCategoria = interfaceDTO.getCod_Categoria();
		this.nomeCategoria = interfaceDTO.getNome_Categoria();
		this.codBarras = interfaceDTO.getCod_Barra();
		this.precoVarejo = interfaceDTO.getPreco_Varejo();
		this.precoVarejoPromocao = interfaceDTO.getPreco_Varejo_Promoc();
		this.embVarejo = interfaceDTO.getEmb_Varejo();
		this.qntEmbVarejo = interfaceDTO.getQtde_Emb_Varejo();
		this.precoAtacado = interfaceDTO.getPreco_Atacado();
		this.precoAtacadoPromocao = interfaceDTO.getPreco_Atacado_Promoc();
		this.embAtacado = interfaceDTO.getEmb_Atacado();
		this.qtdEmbAtacado = interfaceDTO.getQtde_Emb_Atacado();
		this.dataUltimaAlteracaoPreco = interfaceDTO.getDta_Ult_Alt_Preco();
		this.statusVenda = interfaceDTO.getStatus_Venda();
		this.embCompra = interfaceDTO.getEmb_Compra();
		this.qtdEstoqueMenorEmb = interfaceDTO.getQtde_Estq_Menor_Emb();
		this.qtdEstoqueEmbCompra = interfaceDTO.getQtde_Estq_Emb_Compra();
	}

	public Integer getCodLoja() {
		return codLoja;
	}

	public void setCodLoja(Integer codLoja) {
		this.codLoja = codLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public Integer getCodSegmento() {
		return codSegmento;
	}

	public void setCodSegmento(Integer codSegmento) {
		this.codSegmento = codSegmento;
	}

	public String getNomeSegmento() {
		return nomeSegmento;
	}

	public void setNomeSegmento(String nomeSegmento) {
		this.nomeSegmento = nomeSegmento;
	}

	public Long getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(Long codProduto) {
		this.codProduto = codProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Integer getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Integer codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public BigDecimal getPrecoVarejo() {
		return precoVarejo;
	}

	public void setPrecoVarejo(BigDecimal precoVarejo) {
		this.precoVarejo = precoVarejo;
	}

	public BigDecimal getPrecoVarejoPromocao() {
		return precoVarejoPromocao;
	}

	public void setPrecoVarejoPromocao(BigDecimal precoVarejoPromocao) {
		this.precoVarejoPromocao = precoVarejoPromocao;
	}

	public String getEmbVarejo() {
		return embVarejo;
	}

	public void setEmbVarejo(String embVarejo) {
		this.embVarejo = embVarejo;
	}

	public Integer getQntEmbVarejo() {
		return qntEmbVarejo;
	}

	public void setQntEmbVarejo(Integer qntEmbVarejo) {
		this.qntEmbVarejo = qntEmbVarejo;
	}

	public BigDecimal getPrecoAtacado() {
		return precoAtacado;
	}

	public void setPrecoAtacado(BigDecimal precoAtacado) {
		this.precoAtacado = precoAtacado;
	}

	public BigDecimal getPrecoAtacadoPromocao() {
		return precoAtacadoPromocao;
	}

	public void setPrecoAtacadoPromocao(BigDecimal precoAtacadoPromocao) {
		this.precoAtacadoPromocao = precoAtacadoPromocao;
	}

	public String getEmbAtacado() {
		return embAtacado;
	}

	public void setEmbAtacado(String embAtacado) {
		this.embAtacado = embAtacado;
	}

	public Integer getQtdEmbAtacado() {
		return qtdEmbAtacado;
	}

	public void setQtdEmbAtacado(Integer qtdEmbAtacado) {
		this.qtdEmbAtacado = qtdEmbAtacado;
	}

	public Date getDataUltimaAlteracaoPreco() {
		return dataUltimaAlteracaoPreco;
	}

	public void setDataUltimaAlteracaoPreco(Date dataUltimaAlteracaoPreco) {
		this.dataUltimaAlteracaoPreco = dataUltimaAlteracaoPreco;
	}

	public String getStatusVenda() {
		return statusVenda;
	}

	public void setStatusVenda(String statusVenda) {
		this.statusVenda = statusVenda;
	}

	public Integer getEmbCompra() {
		return embCompra;
	}

	public void setEmbCompra(Integer embCompra) {
		this.embCompra = embCompra;
	}

	public Integer getQtdEstoqueMenorEmb() {
		return qtdEstoqueMenorEmb;
	}

	public void setQtdEstoqueMenorEmb(Integer qtdEstoqueMenorEmb) {
		this.qtdEstoqueMenorEmb = qtdEstoqueMenorEmb;
	}

	public Integer getQtdEstoqueEmbCompra() {
		return qtdEstoqueEmbCompra;
	}

	public void setQtdEstoqueEmbCompra(Integer qtdEstoqueEmbCompra) {
		this.qtdEstoqueEmbCompra = qtdEstoqueEmbCompra;
	}

	public interface ProdutoInterfaceDTO {
		Integer getCod_Loja();

		String getNome_Loja();

		Integer getCod_Segmento();

		String getNome_Segmento();

		Long getCod_Produto();

		String getNome_Produto();

		Integer getCod_Categoria();

		String getNome_Categoria();

		String getCod_Barra();

		BigDecimal getPreco_Varejo();

		BigDecimal getPreco_Varejo_Promoc();

		String getEmb_Varejo();

		Integer getQtde_Emb_Varejo();

		BigDecimal getPreco_Atacado();

		BigDecimal getPreco_Atacado_Promoc();

		String getEmb_Atacado();

		Integer getQtde_Emb_Atacado();

		Date getDta_Ult_Alt_Preco();

		String getStatus_Venda();

		Integer getEmb_Compra();

		Integer getQtde_Estq_Menor_Emb();

		Integer getQtde_Estq_Emb_Compra();
	}

}
