package corp.formulacerta.integracao.n8n.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import corp.formulacerta.integracao.mirror.model.OrcTrail;

public class OrcamentoN8N {

	@JsonProperty(value = "id")
	private String id;

	@JsonProperty(value = "altura")
	private BigDecimal altura;

	@JsonProperty(value = "categoria")
	private String categoria;

	@JsonProperty(value = "cod_cliente_formula_certa_id")
	private Integer codCliente;

	@JsonProperty(value = "cod_forma_farmaceutica")
	private Integer codFormaFarmaceutica;

	@JsonProperty(value = "cod_funcionario")
	private Integer codFuncionario;

	@JsonProperty(value = "comprimento")
	private BigDecimal comprimento;

	@JsonProperty(value = "data_cadastro_formula_certa")
	private Date dataCadastroFormulaCerta;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="GMT-3")
	@JsonProperty(value = "data_entrada")
	private Date dataEntrada;

	@JsonProperty(value = "desc_simples")
	private String descricaoSimples;

	@JsonProperty(value = "descricao_completa")
	private String descricaoCompleta;

	@JsonProperty(value = "disponivel")
	private String disponivel;

	@JsonProperty(value = "estoque_atual")
	private Integer estoqueAtual;

	@JsonProperty(value = "filial_id")
	private Integer codFilial;

	@JsonProperty(value = "forma_farmaceutica")
	private String formaFarmaceutica;

	@JsonProperty(value = "largura")
	private BigDecimal largura;

	@JsonProperty(value = "marca")
	private String marca;

	@JsonProperty(value = "modelo")
	private String modelo;

	@JsonProperty(value = "nome_funcionario")
	private String nomeFuncionario;

	@JsonProperty(value = "orcamento_id")
	private Integer numOrcamento;

	@JsonProperty(value = "preco")
	private BigDecimal preco;

	@JsonProperty(value = "preco_oferta")
	private BigDecimal precoOferta;

	@JsonProperty(value = "quantidade")
	private BigDecimal quantidade;

	@JsonProperty(value = "tratamento")
	private String tratamento;

	@JsonProperty(value = "tray_produto_id")
	private String idProdutoTray;

	@JsonProperty(value = "unidade")
	private String unidade;
	
	@JsonProperty(value = "serie")
	private String serie;
	
	
	public OrcamentoN8N() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrcamentoN8N(OrcTrail dto) {
		this.id = dto.getNumOrcamento() + "-" + dto.getSerie() + "-" + dto.getCodFilial();
		this.descricaoSimples = dto.getDescricaoSimples();
		this.descricaoCompleta = dto.getDescricaoCompleta();
		this.codFilial = dto.getCodFilial();
		this.preco = dto.getPreco();
		this.precoOferta = dto.getPrecoOferta();
		this.numOrcamento = dto.getNumOrcamento();
		this.dataEntrada = dto.getDataEntrada();
		this.codCliente = dto.getCodCliente();
		this.quantidade = dto.getQuantidade();
		this.unidade = dto.getUnidade();
		this.nomeFuncionario = dto.getNomeFuncionario();
		this.codFuncionario = dto.getCodFuncionario();
		this.formaFarmaceutica = dto.getFormaFarmaceutica();
		this.tratamento = dto.getTratamento();
		this.codFormaFarmaceutica = dto.getCodFormaFarmaceutica();
		this.disponivel = dto.getDisponivel();
		this.modelo = dto.getModelo();
		this.marca = dto.getMarca();
		this.comprimento = dto.getComprimento();
		this.largura = dto.getLargura();
		this.altura = dto.getAltura();
		this.estoqueAtual = dto.getEstoqueAtual();
		this.categoria = dto.getCategoria();
		this.serie = dto.getSerie();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Integer getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(Integer codCliente) {
		this.codCliente = codCliente;
	}

	public Integer getCodFormaFarmaceutica() {
		return codFormaFarmaceutica;
	}

	public void setCodFormaFarmaceutica(Integer codFormaFarmaceutica) {
		this.codFormaFarmaceutica = codFormaFarmaceutica;
	}

	public Integer getCodFuncionario() {
		return codFuncionario;
	}

	public void setCodFuncionario(Integer codFuncionario) {
		this.codFuncionario = codFuncionario;
	}

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public void setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
	}

	public Date getDataCadastroFormulaCerta() {
		return dataCadastroFormulaCerta;
	}

	public void setDataCadastroFormulaCerta(Date dataCadastroFormulaCerta) {
		this.dataCadastroFormulaCerta = dataCadastroFormulaCerta;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
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

	public String getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(String disponivel) {
		this.disponivel = disponivel;
	}

	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public Integer getCodFilial() {
		return codFilial;
	}

	public void setCodFilial(Integer codFilial) {
		this.codFilial = codFilial;
	}

	public String getFormaFarmaceutica() {
		return formaFarmaceutica;
	}

	public void setFormaFarmaceutica(String formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public Integer getNumOrcamento() {
		return numOrcamento;
	}

	public void setNumOrcamento(Integer numOrcamento) {
		this.numOrcamento = numOrcamento;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public BigDecimal getPrecoOferta() {
		return precoOferta;
	}

	public void setPrecoOferta(BigDecimal precoOferta) {
		this.precoOferta = precoOferta;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public String getIdProdutoTray() {
		return idProdutoTray;
	}

	public void setIdProdutoTray(String idProdutoTray) {
		this.idProdutoTray = idProdutoTray;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
}
