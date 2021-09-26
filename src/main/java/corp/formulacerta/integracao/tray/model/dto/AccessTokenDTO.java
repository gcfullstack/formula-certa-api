package corp.formulacerta.integracao.tray.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import corp.formulacerta.integracao.utils.CustomJsonDateDeserializer;

public class AccessTokenDTO {

	private Integer code;

	private String message;

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("refresh_token")
	private String refreshToken;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonProperty("date_expiration_access_token")
	private Date dateExpirationAccessToken;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonProperty("date_expiration_refresh_token")
	private Date dateExpirationRefreshToken;

	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	@JsonProperty("date_activated")
	private Date dateActivated;

	@JsonProperty("api_host")
	private String apiHost;

	@JsonProperty("store_id")
	private String storeId;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getDateExpirationAccessToken() {
		return dateExpirationAccessToken;
	}

	public void setDateExpirationAccessToken(Date dateExpirationAccessToken) {
		this.dateExpirationAccessToken = dateExpirationAccessToken;
	}

	public Date getDateExpirationRefreshToken() {
		return dateExpirationRefreshToken;
	}

	public void setDateExpirationRefreshToken(Date dateExpirationRefreshToken) {
		this.dateExpirationRefreshToken = dateExpirationRefreshToken;
	}

	public Date getDateActivated() {
		return dateActivated;
	}

	public void setDateActivated(Date dateActivated) {
		this.dateActivated = dateActivated;
	}

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

}
