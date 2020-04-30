package corp.gruposfa.novo.service;

import java.util.List;

import corp.gruposfa.novo.dto.ModeloArquivo;
import corp.gruposfa.novo.dto.NotaFiscalDTO;
import corp.gruposfa.novo.model.NotaFiscal;

public interface NotaFiscalService {
	
	void uploadNotasFiscais(List<ModeloArquivo> arquivos);

	List<NotaFiscalDTO> getNotasFiscaisUsuario(Integer usuario);
	
	void salvar(NotaFiscal notaFiscal);
	
}
