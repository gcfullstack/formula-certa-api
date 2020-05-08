package corp.gruposfa.novo.consinco.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.quero-delivery")
public class QueroDeliveryProperties {

	private String urlApi;

	private String token;

	private String placeId;

	public String getUrlApi() {
		return urlApi;
	}

	public void setUrlApi(String urlApi) {
		this.urlApi = urlApi;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

}
