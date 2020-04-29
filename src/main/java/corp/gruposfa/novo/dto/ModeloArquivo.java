package corp.gruposfa.novo.dto;

public class ModeloArquivo {

	private String base64;
	private String nome;
	private Integer empresa;
	private Integer loja;
	
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
}
