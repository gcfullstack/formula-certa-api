package corp.gruposfa.novo.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NVO_CATEGORIA_QUERO_DELIVERY")
public class CategoriaQueroDelivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	@Basic(optional = false)
	private Integer id;

	@Column(name = "NOME_CATEGORIA")
	private String nomeCategoria;

	@Column(name = "COD_CATEGORIA_CONSINCO")
	private Integer codCategoriaConsinco;

	@Column(name = "COD_CATEGORIA_QUERO_DELIVERY")
	private String codCategoriaQueroDelivery;
	
	public CategoriaQueroDelivery() {
	}

	public CategoriaQueroDelivery(String nomeCategoria, Integer codCategoriaConsinco,
			String codCategoriaQueroDelivery) {
		this.nomeCategoria = nomeCategoria;
		this.codCategoriaConsinco = codCategoriaConsinco;
		this.codCategoriaQueroDelivery = codCategoriaQueroDelivery;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public Integer getCodCategoriaConsinco() {
		return codCategoriaConsinco;
	}

	public void setCodCategoriaConsinco(Integer codCategoriaConsinco) {
		this.codCategoriaConsinco = codCategoriaConsinco;
	}

	public String getCodCategoriaQueroDelivery() {
		return codCategoriaQueroDelivery;
	}

	public void setCodCategoriaQueroDelivery(String codCategoriaQueroDelivery) {
		this.codCategoriaQueroDelivery = codCategoriaQueroDelivery;
	}
	
}
