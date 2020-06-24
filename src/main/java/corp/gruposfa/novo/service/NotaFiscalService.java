package corp.gruposfa.novo.service;

import java.util.List;

import corp.gruposfa.novo.model.NotaFiscal;
import corp.gruposfa.novo.model.dto.ModeloArquivo;
import corp.gruposfa.novo.model.dto.NotaFiscalDTO;
import corp.gruposfa.novo.model.dto.NotaFiscalFiltroDTO;

public interface NotaFiscalService {
	
	void uploadNotasFiscais(List<ModeloArquivo> arquivos);

	List<NotaFiscalDTO> getNotasFiscaisUsuario(Integer usuario);
	
	void salvar(NotaFiscal notaFiscal);
	
	List<NotaFiscalDTO> buscar(NotaFiscalFiltroDTO notaFiscalFiltroDTO);
		
	void aprovar(Integer id, String usuario);
	
	void reprovar(Integer id, String usuario, String motivo);
	
	NotaFiscalDTO getNotaFiscalId(Integer id);
	
	String getEmailUsuarioInterno(String usuario);
}
