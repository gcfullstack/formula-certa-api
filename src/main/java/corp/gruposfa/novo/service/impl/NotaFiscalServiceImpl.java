package corp.gruposfa.novo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import corp.gruposfa.novo.model.NotaFiscal;
import corp.gruposfa.novo.model.dto.ModeloArquivo;
import corp.gruposfa.novo.model.dto.NotaFiscalDTO;
import corp.gruposfa.novo.model.dto.NotaFiscalFiltroDTO;
import corp.gruposfa.novo.repository.NotaFiscalRepository;
import corp.gruposfa.novo.service.NotaFiscalService;
import corp.gruposfa.novo.utils.MethodsUtils;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService{
	
	private final NotaFiscalRepository notaFiscalRepository;
	
	private final DateFormat dfData = new SimpleDateFormat("yyyy-MM-dd");
	private final DateFormat dfDataHora = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	
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
			notaFiscal.setDataUpload(new Date());
			notaFiscal.setUsuario(modeloArquivo.getUsuario());
			NotaFiscal notaFiscalSalva = null;
			try {
				notaFiscalSalva = notaFiscalRepository.saveAndFlush(notaFiscal);
				String imageDataBytes = modeloArquivo.getBase64().substring(modeloArquivo.getBase64().indexOf(",")+1);
				InputStream stream = new ByteArrayInputStream(Base64.decodeBase64(imageDataBytes.getBytes()));
				MethodsUtils.saveToFile(stream, STORAGE_PATH + notaFiscalSalva.getId() + ".pdf");
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

	@Override
	public void aprovar(Integer id) {
		notaFiscalRepository.aprovar(id);
	}

	@Override
	public void reprovar(Integer id, String motivo) {
		notaFiscalRepository.reprovar(id, motivo);
	}
	
	@Override
	public List<NotaFiscalDTO> buscar(NotaFiscalFiltroDTO notaFiscalFiltroDTO) {
		Date minDate = new Date(10800000L);
		Date maxDate = new Date(4102455600000L);
		return notaFiscalRepository.getNotasFiscais(notaFiscalFiltroDTO.getEmpresa(), notaFiscalFiltroDTO.getLoja(), 
				notaFiscalFiltroDTO.getDataVencimentoInicial() != null ? dfData.format(notaFiscalFiltroDTO.getDataVencimentoInicial()) : dfData.format(minDate), notaFiscalFiltroDTO.getDataVencimentoFinal() != null ? dfData.format(notaFiscalFiltroDTO.getDataVencimentoFinal()) : dfData.format(maxDate), 
				notaFiscalFiltroDTO.getDataEmissaoInicial() != null ? dfData.format(notaFiscalFiltroDTO.getDataEmissaoInicial()) : dfData.format(minDate), notaFiscalFiltroDTO.getDataEmissaoFinal() != null ? dfData.format(notaFiscalFiltroDTO.getDataEmissaoFinal()) : dfData.format(maxDate), 
				notaFiscalFiltroDTO.getDataEntradaInicial() != null ? dfData.format(notaFiscalFiltroDTO.getDataEntradaInicial()) : dfData.format(minDate), notaFiscalFiltroDTO.getDataEntradaFinal() != null ? dfData.format(notaFiscalFiltroDTO.getDataEntradaFinal()) : dfData.format(maxDate), 
				notaFiscalFiltroDTO.getDataEnvioInicial() != null ? dfDataHora.format(notaFiscalFiltroDTO.getDataEnvioInicial()) : dfDataHora.format(minDate), notaFiscalFiltroDTO.getDataEnvioFinal() != null ? dfDataHora.format(notaFiscalFiltroDTO.getDataEnvioFinal()) : dfDataHora.format(maxDate), 
				notaFiscalFiltroDTO.getValorMinimo() != null ? notaFiscalFiltroDTO.getValorMinimo() : BigDecimal.ZERO, notaFiscalFiltroDTO.getValorMaximo() != null ? notaFiscalFiltroDTO.getValorMaximo() : new BigDecimal(2147483647).setScale(2), notaFiscalFiltroDTO.getStatusSelecionados()).stream().map(x -> new NotaFiscalDTO(x)).collect(Collectors.toList());
	}

}
