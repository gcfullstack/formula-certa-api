package corp.gruposfa.novo.model.dto;

public class CategoriaQueroDeliveryDTO {
	
	private Integer id;

	private String nomeCategoria;

	private Integer codCategoriaConsinco;

	private String codCategoriaQueroDelivery;
	
	public CategoriaQueroDeliveryDTO() {
	}
	
	public CategoriaQueroDeliveryDTO(String nomeCategoria, Integer codCategoriaConsinco) {
		this.nomeCategoria = nomeCategoria;
		this.codCategoriaConsinco = codCategoriaConsinco;
	}

	public CategoriaQueroDeliveryDTO(Integer id, String nomeCategoria, Integer codCategoriaConsinco,
			String codCategoriaQueroDelivery) {
		this.id = id;
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
