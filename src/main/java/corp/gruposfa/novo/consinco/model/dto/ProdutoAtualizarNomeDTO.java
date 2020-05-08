package corp.gruposfa.novo.consinco.model.dto;

public class ProdutoAtualizarNomeDTO {
	
	private String nome;
	
	public ProdutoAtualizarNomeDTO() {
	}

	public ProdutoAtualizarNomeDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
