package corp.gruposfa.novo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import corp.gruposfa.novo.dto.ModeloArquivo;
import corp.gruposfa.novo.dto.NotaFiscalDTO;
import corp.gruposfa.novo.model.NotaFiscal;
import corp.gruposfa.novo.repository.NotaFiscalRepository;
import corp.gruposfa.novo.service.NotaFiscalService;
import corp.gruposfa.novo.utils.Utils;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService{
	
	private final NotaFiscalRepository notaFiscalRepository;
	
	private static final String STORAGE_PATH = "/mnt/sfa-fs-app/api-novo/notasfiscais/";
	//private static final String STORAGE_PATH = "S:/api-novo/notasfiscais/";
	
	public NotaFiscalServiceImpl(NotaFiscalRepository notaFiscalRepository) {
		this.notaFiscalRepository = notaFiscalRepository;
	}

	@Override
	public void uploadNotasFiscais(List<ModeloArquivo> arquivos) {
		for (ModeloArquivo modeloArquivo : arquivos) {
			NotaFiscal notaFiscal = new NotaFiscal();
			notaFiscal.setNomeArquivo(modeloArquivo.getNome());
			notaFiscal.setEmpresa(modeloArquivo.getEmpresa());
			notaFiscal.setLoja(modeloArquivo.getLoja());
			notaFiscal.setEnviado(0);
			NotaFiscal notaFiscalSalva = null;
			try {
				notaFiscalSalva = notaFiscalRepository.saveAndFlush(notaFiscal);
				String imageDataBytes = modeloArquivo.getBase64().substring(modeloArquivo.getBase64().indexOf(",")+1);
				InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(imageDataBytes.getBytes()));
				Utils.saveToFile(stream, STORAGE_PATH + notaFiscalSalva.getId() + ".pdf");
			} catch (Exception e) {
				if(notaFiscalSalva != null) {
					notaFiscalRepository.delete(notaFiscalSalva);
				}
			}
		}
	}
	
	@Override
	public List<NotaFiscalDTO> getNotasFiscaisUsuario(Integer usuario) {
		return notaFiscalRepository.getNotasFiscaisUsuario(usuario).stream().map(x -> new NotaFiscalDTO(x)).collect(Collectors.toList());
	}

	@Override
	public void salvar(NotaFiscal notaFiscal) {
		notaFiscalRepository.saveAndFlush(notaFiscal);
	}

}
