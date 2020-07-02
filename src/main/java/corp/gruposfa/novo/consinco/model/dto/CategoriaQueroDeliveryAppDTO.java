package corp.gruposfa.novo.consinco.model.dto;

public class CategoriaQueroDeliveryAppDTO {

	private String id;

	private String nome;

	private Boolean isAtivo;
	
	public CategoriaQueroDeliveryAppDTO() {
		super();
	}
	
	public CategoriaQueroDeliveryAppDTO(String id, String nome, Boolean isAtivo) {
		this.id = id;
		this.nome = nome;
		this.isAtivo = isAtivo;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getIsAtivo() {
		return isAtivo;
	}

	public void setIsAtivo(Boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

}
