package corp.formulacerta.integracao.n8n.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import corp.formulacerta.integracao.n8n.model.dto.LogConsultaFormulaCertaDTO;
import corp.formulacerta.integracao.n8n.model.dto.OrcamentoN8N;
import corp.formulacerta.integracao.n8n.service.LogOrcamentoN8NService;

@Service
public class LogOrcamentoN8NServiceImpl implements LogOrcamentoN8NService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public LogConsultaFormulaCertaDTO salvarLogOrcamento(LogConsultaFormulaCertaDTO dto) {
		try {
			String json = new ObjectMapper().writeValueAsString(dto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhcmVhIjoiVEkiLCJwZXJmaXMiOjEsInN1YiI6Imd1aWxoZXJtZS5jYW50b24iLCJub21lIjoiZ3VpbGhlcm1lLmNhbnRvbiIsImlkIjoiMzM0IiwiZXhwIjoxNjQwMDQ0ODAwLCJpbmZvIjoibnVsbCJ9.W31RD779qeNDLsVWAxgomUH-KaleV4TGNG7e8ibJdVEOXIHXhQDhkwVWMSC_3Yu8b9Zid_R7fVAMuUsSInUOeg");
			String url = "https://webhooks.attivecare.com.br/webhook/api/attivecare-formulacerta/2fc9eb8f-c9ee-4831-b037-5ba84110a8b4/savelog";
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			return restTemplate.postForEntity(url, request, LogConsultaFormulaCertaDTO.class).getBody();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao salvar um log de orçamento da API do N8N");
		}
	}

	@Override
	public LogConsultaFormulaCertaDTO buscarUltimoRegistroLog() {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhcmVhIjoiVEkiLCJwZXJmaXMiOjEsInN1YiI6Imd1aWxoZXJtZS5jYW50b24iLCJub21lIjoiZ3VpbGhlcm1lLmNhbnRvbiIsImlkIjoiMzM0IiwiZXhwIjoxNjQwMDQ0ODAwLCJpbmZvIjoibnVsbCJ9.W31RD779qeNDLsVWAxgomUH-KaleV4TGNG7e8ibJdVEOXIHXhQDhkwVWMSC_3Yu8b9Zid_R7fVAMuUsSInUOeg");
			String url = "https://webhooks.attivecare.com.br/webhook/api/attivecare-formulacerta/2fc9eb8f-c9ee-4831-b037-5ba84110a8b4/getlast";
			HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
			LogConsultaFormulaCertaDTO[] array = restTemplate.exchange(
				    url, HttpMethod.GET, requestEntity, LogConsultaFormulaCertaDTO[].class).getBody();
			List<LogConsultaFormulaCertaDTO> list= Arrays.asList(array);
			return list.isEmpty() ? null : list.get(0);
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao buscar os orçamentos na API do N8N");
		}
	}

	
}
