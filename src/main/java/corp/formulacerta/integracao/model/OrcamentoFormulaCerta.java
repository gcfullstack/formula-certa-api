package corp.formulacerta.integracao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FC15000")
public class OrcamentoFormulaCerta implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrcamentoFormulaCertaPK id;

	public OrcamentoFormulaCertaPK getId() {
		return id;
	}

	public void setId(OrcamentoFormulaCertaPK id) {
		this.id = id;
	}

}
