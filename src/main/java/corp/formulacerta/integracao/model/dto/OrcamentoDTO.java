package corp.formulacerta.integracao.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrcamentoDTO {

	private String descricaoSimples;

	private String descricaoCompleta;

	private Integer codFilial;

	private BigDecimal preco;

	private BigDecimal precoOferta;

	private Integer numOrcamento;

	private Date dataEntrada;

	private Integer codCliente;

	private BigDecimal quantidade;

	private String unidade;

	private Integer codFuncionario;

	private String formaFarmaceutica;

	private String tratamento;

	private Integer codFormaFarmaceutica;

	private String disponivel;

	private String modelo;

	private String marca;

	private BigDecimal comprimento;

	private BigDecimal largura;

	private BigDecimal altura;

	private Integer estoqueAtual;

	private String categoria;
	
	private String idProdutoTray;
	
	public OrcamentoDTO() {
		super();
	}
	
	public OrcamentoDTO(Integer numeroOrcamento, String descricaoSimples, Integer codFormaFarmaceutica, String formaFarmaceutica, BigDecimal preco, BigDecimal precoOferta, Date dataEntrada, BigDecimal quantidade, String unidade, String idProdutoTray) {
		this.numOrcamento = numeroOrcamento;
		this.descricaoSimples = descricaoSimples;
		this.codFormaFarmaceutica = codFormaFarmaceutica;
		this.formaFarmaceutica = formaFarmaceutica;
		this.preco = preco;
		this.precoOferta = precoOferta;
		this.dataEntrada = dataEntrada;
		this.quantidade = quantidade;
		this.unidade = unidade;
		this.idProdutoTray = idProdutoTray;
	}

	public OrcamentoDTO(OrcamentoInterfaceDTO orcamentoInterface) {
		this.descricaoSimples = orcamentoInterface.getDescricaoSimples();
		this.descricaoCompleta = orcamentoInterface.getDescricaoCompleta();
		this.codFilial = orcamentoInterface.getCodFilial();
		this.preco = orcamentoInterface.getPreco();
		this.precoOferta = orcamentoInterface.getPrecoOferta();
		this.numOrcamento = orcamentoInterface.getNumOrcamento();
		this.dataEntrada = orcamentoInterface.getDataEntrada();
		this.codCliente = orcamentoInterface.getCodCliente();
		this.quantidade = orcamentoInterface.getQuantidade();
		this.unidade = orcamentoInterface.getUnidade();
		this.codFuncionario = orcamentoInterface.getCodFuncionario();
		this.formaFarmaceutica = orcamentoInterface.getFormaFarmaceutica();
		this.tratamento = orcamentoInterface.getTratamento();
		this.codFormaFarmaceutica = orcamentoInterface.getCodFormaFarmaceutica();
		this.disponivel = orcamentoInterface.getDisponivel();
		this.modelo = orcamentoInterface.getModelo();
		this.marca = orcamentoInterface.getMarca();
		this.comprimento = orcamentoInterface.getComprimento();
		this.largura = orcamentoInterface.getLargura();
		this.altura = orcamentoInterface.getAltura();
		this.estoqueAtual = orcamentoInterface.getEstoqueAtual();
		this.categoria = orcamentoInterface.getCategoria();
	}

	public String getDescricaoSimples() {
		return descricaoSimples;
	}

	public void setDescricaoSimples(String descricaoSimples) {
		this.descricaoSimples = descricaoSimples;
	}

	public String getDescricaoCompleta() {
		return descricaoCompleta;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public Integer getCodFilial() {
		return codFilial;
	}

	public void setCodFilial(Integer codFilial) {
		this.codFilial = codFilial;
	}

	public BigDecimal getPrecoOferta() {
		return precoOferta;
	}

	public void setPrecoOferta(BigDecimal precoOferta) {
		this.precoOferta = precoOferta;
	}

	public Integer getNumOrcamento() {
		return numOrcamento;
	}

	public void setNumOrcamento(Integer numOrcamento) {
		this.numOrcamento = numOrcamento;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Integer getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(Integer codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	public String getFormaFarmaceutica() {
		return formaFarmaceutica;
	}

	public void setFormaFarmaceutica(String formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public Integer getCodFormaFarmaceutica() {
		return codFormaFarmaceutica;
	}

	public void setCodFormaFarmaceutica(Integer codFormaFarmaceutica) {
		this.codFormaFarmaceutica = codFormaFarmaceutica;
	}

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public void setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public String getIdProdutoTray() {
		return idProdutoTray;
	}

	public void setIdProdutoTray(String idProdutoTray) {
		this.idProdutoTray = idProdutoTray;
	}

	public interface OrcamentoInterfaceDTO {
		public String getDescricaoSimples();

		public String getDescricaoCompleta();

		public Integer getCodFilial();
		
		public BigDecimal getPreco();

		public BigDecimal getPrecoOferta();

		public Integer getNumOrcamento();

		public Date getDataEntrada();

		public Integer getCodCliente();

		public BigDecimal getQuantidade();

		public String getUnidade();

		public Integer getCodFuncionario();

		public String getFormaFarmaceutica();

		public String getTratamento();

		public Integer getCodFormaFarmaceutica();

		public String getDisponivel();

		public String getModelo();

		public String getMarca();

		public BigDecimal getComprimento();

		public BigDecimal getLargura();

		public BigDecimal getAltura();

		public Integer getEstoqueAtual();

		public String getCategoria();
	}

}
