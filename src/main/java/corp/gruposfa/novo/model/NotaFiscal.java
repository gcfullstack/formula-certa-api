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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "NVO_NOTAS_FISCAIS")
public class NotaFiscal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NVO_ID")
	@Basic(optional = false)
	private Integer id;

	@Column(name = "NVO_EMPRESA")
	private Integer empresa;

	@Column(name = "NVO_LOJA")
	private Integer loja;

	@Column(name = "NVO_NUMERO_NOTA")
	private String numeroNota;
	
    @Temporal(TemporalType.DATE)
	@Column(name = "NVO_DATA_EMISSAO")
	private Date dataEmissao;
    
    @Temporal(TemporalType.DATE)
	@Column(name = "NVO_DATA_ENTRADA")
	private Date dataEntrada;

	@Column(name = "NVO_DATA_UPLOAD")
	private Date dataUpload;

	@Column(name = "NVO_VALOR")
	private BigDecimal valor;

	@Column(name = "NVO_USUARIO")
	private String usuario;

	@Column(name = "NVO_CNPJ_FORNECEDOR")
	private String cnpjFornecedor;

	@Column(name = "NVO_NOME_FORNECEDOR")
	private String nomeFornecedor;

	@Column(name = "NVO_NOME_ARQUIVO")
	private String nomeArquivo;

	@Column(name = "NVO_ENVIADO")
	private Integer enviado;
	
	@Column(name = "NVO_DATA_ENVIO")
	private Date dataEnvio;
    
    @Temporal(TemporalType.DATE)
	@Column(name = "NVO_DATA_VENCIMENTO")
	private Date dataVencimento;

	@Column(name = "NVO_MOTIVO_REPROVACAO")
	private String motivoReprovacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}

	public Integer getLoja() {
		return loja;
	}

	public void setLoja(Integer loja) {
		this.loja = loja;
	}

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCnpjFornecedor() {
		return cnpjFornecedor;
	}

	public void setCnpjFornecedor(String cnpjFornecedor) {
		this.cnpjFornecedor = cnpjFornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public Integer getEnviado() {
		return enviado;
	}

	public void setEnviado(Integer enviado) {
		this.enviado = enviado;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getMotivoReprovacao() {
		return motivoReprovacao;
	}

	public void setMotivoReprovacao(String motivoReprovacao) {
		this.motivoReprovacao = motivoReprovacao;
	}
}
