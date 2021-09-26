package corp.formulacerta.integracao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrcamentoFormulaCertaPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CDFIL")
	private String codFilial;

	@Column(name = "NRORC")
	private Long numOrcamento;

	public String getCodFilial() {
		return codFilial;
	}

	public void setCodFilial(String codFilial) {
		this.codFilial = codFilial;
	}

	public Long getNumOrcamento() {
		return numOrcamento;
	}

	public void setNumOrcamento(Long numOrcamento) {
		this.numOrcamento = numOrcamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFilial == null) ? 0 : codFilial.hashCode());
		result = prime * result + ((numOrcamento == null) ? 0 : numOrcamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrcamentoFormulaCertaPK other = (OrcamentoFormulaCertaPK) obj;
		if (codFilial == null) {
			if (other.codFilial != null)
				return false;
		} else if (!codFilial.equals(other.codFilial))
			return false;
		if (numOrcamento == null) {
			if (other.numOrcamento != null)
				return false;
		} else if (!numOrcamento.equals(other.numOrcamento))
			return false;
		return true;
	}
	
	

}
