package corp.gruposfa.novo.consinco.model.dto;

public class CategoriaDTO {

	private String nome;

	private Boolean isAtivo;
	

	public CategoriaDTO() {
	}

	public CategoriaDTO(String nome, Boolean isAtivo) {
		this.nome = nome;
		this.isAtivo = isAtivo;
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
