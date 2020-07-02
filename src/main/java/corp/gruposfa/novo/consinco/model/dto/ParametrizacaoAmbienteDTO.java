package corp.gruposfa.novo.consinco.model.dto;

public class ParametrizacaoAmbienteDTO {

	private String placeId;

	private String token;

	private String ambiente;
	
	private String url;
	
	public ParametrizacaoAmbienteDTO() {
	}

	public ParametrizacaoAmbienteDTO(String placeId, String token, String ambiente, String url) {
		this.placeId = placeId;
		this.token = token;
		this.ambiente = ambiente;
		this.url = url;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
