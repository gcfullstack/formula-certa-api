package corp.gruposfa.novo.consinco.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application.quero-delivery")
public class QueroDeliveryProperties {

	private String urlApiProd;

	private String tokenProd;

	private String placeIdProd;
	
	private String urlApiHml;
	
	private String tokenHml;
	
	private String placeIdHml;

	public String getTokenProd() {
		return tokenProd;
	}

	public void setTokenProd(String tokenProd) {
		this.tokenProd = tokenProd;
	}

	public String getPlaceIdProd() {
		return placeIdProd;
	}

	public void setPlaceIdProd(String placeIdProd) {
		this.placeIdProd = placeIdProd;
	}

	public String getTokenHml() {
		return tokenHml;
	}

	public void setTokenHml(String tokenHml) {
		this.tokenHml = tokenHml;
	}

	public String getPlaceIdHml() {
		return placeIdHml;
	}

	public void setPlaceIdHml(String placeIdHml) {
		this.placeIdHml = placeIdHml;
	}

	public String getUrlApiProd() {
		return urlApiProd;
	}

	public void setUrlApiProd(String urlApiProd) {
		this.urlApiProd = urlApiProd;
	}

	public String getUrlApiHml() {
		return urlApiHml;
	}

	public void setUrlApiHml(String urlApiHml) {
		this.urlApiHml = urlApiHml;
	}
	
	
}
