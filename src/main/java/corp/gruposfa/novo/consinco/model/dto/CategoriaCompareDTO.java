package corp.gruposfa.novo.consinco.model.dto;

public class CategoriaCompareDTO {
	
	private Integer codCategoria;
	
	private String nomeCategoria;
	
	public CategoriaCompareDTO() {
	}
	
	

	public CategoriaCompareDTO(CategoriaCompareInterfaceDTO interfaceDTO) {
		this.codCategoria = interfaceDTO.getCodCategoria();
		this.nomeCategoria = interfaceDTO.getNomeCategoria();
	}



	public CategoriaCompareDTO(Integer codCategoria, String nomeCategoria) {
		this.codCategoria = codCategoria;
		this.nomeCategoria = nomeCategoria;
	}

	public Integer getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(Integer codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	public interface CategoriaCompareInterfaceDTO {
		Integer getCodCategoria();

		String getNomeCategoria();
	}

}
