package corp.gruposfa.novo.consinco.model.dto;

public class ProdutoAtualizarStatusDTO {

	private String status;
	
	public ProdutoAtualizarStatusDTO() {
	}

	public ProdutoAtualizarStatusDTO(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
