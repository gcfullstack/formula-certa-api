package corp.formulacerta.integracao.n8n.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import corp.formulacerta.integracao.n8n.model.dto.OrcamentoN8N;
import corp.formulacerta.integracao.n8n.service.OrcamentoN8NService;
import corp.formulacerta.integracao.utils.ConstantsUtils;
import corp.formulacerta.integracao.utils.MethodsUtils;

@Service
public class OrcamentoN8NServiceImpl implements OrcamentoN8NService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public OrcamentoN8N[] buscarOrcamento(Integer page) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhcmVhIjoiVEkiLCJwZXJmaXMiOjEsInN1YiI6Imd1aWxoZXJtZS5jYW50b24iLCJub21lIjoiZ3VpbGhlcm1lLmNhbnRvbiIsImlkIjoiMzM0IiwiZXhwIjoxNjQwMDQ0ODAwLCJpbmZvIjoibnVsbCJ9.W31RD779qeNDLsVWAxgomUH-KaleV4TGNG7e8ibJdVEOXIHXhQDhkwVWMSC_3Yu8b9Zid_R7fVAMuUsSInUOeg");
			String url = "https://webhooks.coocreation.com.br/webhook/api/attivecare-formulacerta/7e9559f6-7638-48fd-b2d5-54433818d6de/getall?page=" + page;
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			return  restTemplate.exchange(
			    url, HttpMethod.GET, requestEntity, OrcamentoN8N[].class).getBody();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao cadastrar o Produto na API Tray");
		}
	}
	@Override
	public Object salvarOrcamento(Object dto, Class clazz) {
		try {
			String json = new ObjectMapper().writeValueAsString(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhcmVhIjoiVEkiLCJwZXJmaXMiOjEsInN1YiI6Imd1aWxoZXJtZS5jYW50b24iLCJub21lIjoiZ3VpbGhlcm1lLmNhbnRvbiIsImlkIjoiMzM0IiwiZXhwIjoxNjQwMDQ0ODAwLCJpbmZvIjoibnVsbCJ9.W31RD779qeNDLsVWAxgomUH-KaleV4TGNG7e8ibJdVEOXIHXhQDhkwVWMSC_3Yu8b9Zid_R7fVAMuUsSInUOeg");
			String url = "https://webhooks.coocreation.com.br/webhook/api/attivecare-formulacerta/7e9559f6-7638-48fd-b2d5-54433818d6de/upsert";
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			return restTemplate.postForEntity(url, request, clazz).getBody();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao cadastrar o Produto na API Tray");
		}
	}
	
	@Override
	public Object atualizarOrcamento(Object dto, Class clazz) {
		try {
			String json = new ObjectMapper().writeValueAsString(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhcmVhIjoiVEkiLCJwZXJmaXMiOjEsInN1YiI6Imd1aWxoZXJtZS5jYW50b24iLCJub21lIjoiZ3VpbGhlcm1lLmNhbnRvbiIsImlkIjoiMzM0IiwiZXhwIjoxNjQwMDQ0ODAwLCJpbmZvIjoibnVsbCJ9.W31RD779qeNDLsVWAxgomUH-KaleV4TGNG7e8ibJdVEOXIHXhQDhkwVWMSC_3Yu8b9Zid_R7fVAMuUsSInUOeg");
			String url = "https://webhooks.coocreation.com.br/webhook/api/attivecare-formulacerta/7e9559f6-7638-48fd-b2d5-54433818d6de/update_tray_id";
					HttpEntity<String> request = new HttpEntity<String>(json, headers);
			return restTemplate.postForEntity(url, request, clazz).getBody();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao cadastrar o Produto na API Tray");
		}
	}
	
	
	@Override
	public List<OrcamentoN8N> buscarOrcamentoByDateLessThanCurrentDate(Date data) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhcmVhIjoiVEkiLCJwZXJmaXMiOjEsInN1YiI6Imd1aWxoZXJtZS5jYW50b24iLCJub21lIjoiZ3VpbGhlcm1lLmNhbnRvbiIsImlkIjoiMzM0IiwiZXhwIjoxNjQwMDQ0ODAwLCJpbmZvIjoibnVsbCJ9.W31RD779qeNDLsVWAxgomUH-KaleV4TGNG7e8ibJdVEOXIHXhQDhkwVWMSC_3Yu8b9Zid_R7fVAMuUsSInUOeg");
			String url = "https://webhooks.coocreation.com.br/webhook/api/attivecare-formulacerta/7e9559f6-7638-48fd-b2d5-54433818d6de/get-by-date?data=" + MethodsUtils.formatarDataString(data, ConstantsUtils.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			OrcamentoN8N[] array = restTemplate.exchange(
			    url, HttpMethod.GET, requestEntity, OrcamentoN8N[].class).getBody();
			return Arrays.asList(array);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao cadastrar o Produto na API Tray");
		}
	}

	
}
