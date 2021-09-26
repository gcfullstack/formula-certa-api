package corp.formulacerta.integracao.tray.service;

import corp.formulacerta.integracao.tray.model.dto.AccessTokenDTO;
import corp.formulacerta.integracao.tray.model.dto.ProdutoDTO;

public interface AutenticacaoTrayService {
	
	public AccessTokenDTO autenticar();
	
	public String getToken();
	
}
