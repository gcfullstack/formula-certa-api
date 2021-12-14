package corp.formulacerta.integracao.mirror.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import corp.formulacerta.integracao.model.dto.OrcamentoDTO;

public class OrcTrail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String descricaoSimples;

	private String descricaoCompleta;

	private String serie;

	private Integer codFilial;

	private BigDecimal preco;

	private BigDecimal precoOferta;

	private Integer numOrcamento;

	private Date dataEntrada;

	private Date dataCadastroFormulaCerta;

	private Integer codCliente;

	private BigDecimal quantidade;

	private String unidade;

	private String nomeFuncionario;

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

	public OrcTrail() {
		super();
	}

	public OrcTrail(OrcamentoDTO dto) {
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
		this.dataCadastroFormulaCerta = dto.getDataCadastro();
		this.serie = dto.getSerie();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public interface OrcamentoInterfaceDTO {
		public Integer getId();

		public String getDescricaoSimples();

		public String getDescricaoCompleta();

		public Integer getCodFilial();

		public BigDecimal getPrecoOferta();

		public Integer getNumOrcamento();

		public Date getDataEntrada();

		public Integer getCodCliente();

		public BigDecimal getQuantidade();

		public String getUnidade();

		public String getNomeFuncionario();

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

		public BigDecimal getPreco();
	}

}
