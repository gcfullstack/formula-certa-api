package corp.formulacerta.integracao.tray.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import corp.formulacerta.integracao.properties.TrayProperties;
import corp.formulacerta.integracao.tray.model.dto.ProdutoCriadoDTO;
import corp.formulacerta.integracao.tray.model.dto.ProdutoDTO;
import corp.formulacerta.integracao.tray.service.AutenticacaoTrayService;
import corp.formulacerta.integracao.tray.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private RestTemplate restTemplate;

	private final TrayProperties trayProperties;

	private final AutenticacaoTrayService autenticacaoService;

	public ProdutoServiceImpl(TrayProperties trayProperties, AutenticacaoTrayService autenticacaoService){
		this.trayProperties = trayProperties;
		this.autenticacaoService = autenticacaoService;
	}

	@Override
	public ProdutoCriadoDTO cadastrarProduto(ProdutoDTO produto) {
		try {
			String json = new ObjectMapper().writeValueAsString(produto);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url = trayProperties.getUrl() + "products/";
			String access_token = "?access_token=" + autenticacaoService.getToken();
			HttpEntity<String> request = new HttpEntity<String>(json, headers);
			return restTemplate.postForEntity(url + access_token, request, ProdutoCriadoDTO.class).getBody();
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao cadastrar o Produto na API Tray");
		}
	}

}
