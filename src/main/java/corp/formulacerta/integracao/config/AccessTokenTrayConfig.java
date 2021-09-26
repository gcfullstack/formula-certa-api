package corp.formulacerta.integracao.config;


import corp.formulacerta.integracao.tray.model.dto.AccessTokenDTO;

public class AccessTokenTrayConfig {

	private static AccessTokenDTO token = new AccessTokenDTO();

	public static AccessTokenDTO getToken() {
		return token;
	}

	public static void setToken(AccessTokenDTO token) {
		AccessTokenTrayConfig.token = token;
	}

}