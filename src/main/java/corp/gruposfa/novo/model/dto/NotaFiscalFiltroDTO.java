package corp.gruposfa.novo.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class NotaFiscalFiltroDTO {

	private Integer empresa;
	private Integer loja;
	private Date dataEmissaoInicial;
	private Date dataEmissaoFinal;
	private Date dataEntradaInicial;
	private Date dataEntradaFinal;
	private Date dataEnvioInicial;
	private Date dataEnvioFinal;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	private Date dataVencimentoInicial;
	private Date dataVencimentoFinal;
	private List<Integer> statusSelecionados;
	
	public NotaFiscalFiltroDTO() {

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

	public Date getDataEmissaoInicial() {
		return dataEmissaoInicial;
	}

	public void setDataEmissaoInicial(Date dataEmissaoInicial) {
		this.dataEmissaoInicial = dataEmissaoInicial;
	}

	public Date getDataEmissaoFinal() {
		return dataEmissaoFinal;
	}

	public void setDataEmissaoFinal(Date dataEmissaoFinal) {
		this.dataEmissaoFinal = dataEmissaoFinal;
	}

	public Date getDataEntradaInicial() {
		return dataEntradaInicial;
	}

	public void setDataEntradaInicial(Date dataEntradaInicial) {
		this.dataEntradaInicial = dataEntradaInicial;
	}

	public Date getDataEntradaFinal() {
		return dataEntradaFinal;
	}

	public void setDataEntradaFinal(Date dataEntradaFinal) {
		this.dataEntradaFinal = dataEntradaFinal;
	}

	public Date getDataEnvioInicial() {
		return dataEnvioInicial;
	}

	public void setDataEnvioInicial(Date dataEnvioInicial) {
		this.dataEnvioInicial = dataEnvioInicial;
	}

	public Date getDataEnvioFinal() {
		return dataEnvioFinal;
	}

	public void setDataEnvioFinal(Date dataEnvioFinal) {
		this.dataEnvioFinal = dataEnvioFinal;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public Date getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(Date dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	public List<Integer> getStatusSelecionados() {
		return statusSelecionados;
	}

	public void setStatusSelecionados(List<Integer> statusSelecionados) {
		this.statusSelecionados = statusSelecionados;
	}
}
