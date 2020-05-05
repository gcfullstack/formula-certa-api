package corp.gruposfa.novo.dto;

import java.math.BigDecimal;
import java.util.Date;

public class NotaFiscalDTO {

	private Integer id;
	private Integer empresa;
	private Integer loja;
	private String numeroNota;
	private Date dataEmissao;
	private Date dataEntrada;
	private Date dataUpload;
	private BigDecimal valor;
	private String usuario;
	private String cnpjFornecedor;
	private String nomeFornecedor;
	private String nomeArquivo;
	private Integer enviado;
	private Date dataEnvio;
	private Date dataVencimento;
	
	public NotaFiscalDTO(NotaFiscalInterfaceDTO x) {
		this.id = x.getId();
		this.empresa = x.getEmpresa();
		this.loja = x.getLoja();
		this.numeroNota = x.getNumeroNota();
		this.dataEmissao = x.getDataEmissao();
		this.dataEntrada = x.getDataEntrada();
		this.dataUpload = x.getDataUpload();
		this.valor = x.getValor();
		this.usuario = x.getUsuario();
		this.cnpjFornecedor = x.getCnpjFornecedor();
		this.nomeFornecedor = x.getNomeFornecedor();
		this.nomeArquivo = x.getNomeArquivo();
		this.enviado = x.getEnviado();
		this.dataEnvio = x.getDataEnvio();
		this.dataVencimento = x.getDataVencimento();
	}

	public NotaFiscalDTO() {
		
	}
	
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

	public interface NotaFiscalInterfaceDTO {

		Integer getId();
		Integer getEmpresa();
		Integer getLoja();
		String getNumeroNota();
		Date getDataEmissao();
		Date getDataEntrada();
		Date getDataUpload();
		BigDecimal getValor();
		String getUsuario();
		String getCnpjFornecedor();
		String getNomeFornecedor();
		String getNomeArquivo();
		Integer getEnviado();
		Date getDataEnvio();
		Date getDataVencimento();
		
	}
}
