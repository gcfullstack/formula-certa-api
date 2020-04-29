package corp.gruposfa.novo.service;

import java.util.List;

import corp.gruposfa.novo.dto.ModeloArquivo;

public interface NotaFiscalService {
	
	void uploadNotasFiscais(List<ModeloArquivo> arquivos);

}
