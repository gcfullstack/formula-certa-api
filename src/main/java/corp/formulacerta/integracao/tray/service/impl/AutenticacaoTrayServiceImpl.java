package corp.formulacerta.integracao.tray.service.impl;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import corp.formulacerta.integracao.config.AccessTokenTrayConfig;
import corp.formulacerta.integracao.properties.TrayProperties;
import corp.formulacerta.integracao.tray.model.dto.AccessTokenDTO;
import corp.formulacerta.integracao.tray.service.AutenticacaoTrayService;

@Service
public class AutenticacaoTrayServiceImpl implements AutenticacaoTrayService {

	@Autowired
	private RestTemplate restTemplate;

	private final TrayProperties trayProperties;

	public AutenticacaoTrayServiceImpl(TrayProperties trayProperties) {
		this.trayProperties = trayProperties;
	}

	@Override
	public AccessTokenDTO autenticar() {
		String url = trayProperties.getUrl() + "auth/";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("consumer_key", trayProperties.getConsumerKey());
		map.add("consumer_secret", trayProperties.getConsumerSecret());
		map.add("code", trayProperties.getCode());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<AccessTokenDTO> response = null;
		try {
			response = restTemplate.postForEntity(url, request, AccessTokenDTO.class);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao autenticar: " + e.getMessage());
		}

		AccessTokenTrayConfig.setToken(response.getBody());

		return response.getBody();

	}

	public AccessTokenDTO refreshToken() {
		String url = trayProperties.getUrl() + "auth/?refresh_token="
				+ AccessTokenTrayConfig.getToken().getRefreshToken();

		ResponseEntity<AccessTokenDTO> response = null;

		try {
			response = restTemplate.getForEntity(url, AccessTokenDTO.class);
			AccessTokenTrayConfig.setToken(response.getBody());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return response.getBody();

	}

	@Override
	public String getToken() {
		if (AccessTokenTrayConfig.getToken().getAccessToken() == null) {
			return autenticar().getAccessToken();
		}

		if (validateTokenExpiration(AccessTokenTrayConfig.getToken().getDateExpirationAccessToken())) {
			return AccessTokenTrayConfig.getToken().getAccessToken();
		} else {
			if (validateTokenExpiration(AccessTokenTrayConfig.getToken().getDateExpirationRefreshToken())) {
				return refreshToken().getAccessToken();
			} else {
				return autenticar().getAccessToken();
			}
		}
	}
	
	private boolean validateTokenExpiration(Date dateExpiration) {
		return Instant.now().isBefore(dateExpiration.toInstant());
	}

}
