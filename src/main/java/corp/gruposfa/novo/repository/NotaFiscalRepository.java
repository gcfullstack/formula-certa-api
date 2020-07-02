package corp.gruposfa.novo.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import corp.gruposfa.novo.model.NotaFiscal;
import corp.gruposfa.novo.model.dto.NotaFiscalDTO.NotaFiscalInterfaceDTO;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Integer> {

	@Query(value = "select NVO_ID as id, NVO_EMPRESA as empresa, NVO_LOJA as loja, NVO_NUMERO_NOTA as numeroNota, NVO_DATA_EMISSAO as dataEmissao, NVO_DATA_ENTRADA as dataEntrada, NVO_DATA_UPLOAD as dataUpload, NVO_DATA_ENVIO as dataEnvio, NVO_VALOR as valor, NVO_USUARIO as usuario, NVO_CNPJ_FORNECEDOR as cnpjFornecedor, NVO_NOME_FORNECEDOR as nomeFornecedor, NVO_NOME_ARQUIVO as nomeArquivo, NVO_ENVIADO as enviado, NVO_DATA_VENCIMENTO as dataVencimento, NVO_MOTIVO_REPROVACAO as motivoReprovacao, NVO_USUARIO_APROVACAO as usuarioAprovacao FROM NVO_NOTAS_FISCAIS INNER JOIN SFAPortal.dbo.ACE_USUARIO ON (ACE_USUARIO.USU_ID = :usuario) where NVO_EMPRESA IN (SELECT ACC_ID FROM SFAPortal.dbo.ACE_USUARIO_COLIGADA WHERE USU_ID = :usuario AND AUC_ATIVO = 1) and NVO_LOJA IN (SELECT SFAPortal.dbo.ACE_FILIAL.ACF_ID FROM SFAPortal.dbo.ACE_USUARIO_COLIGADA_FILIAL INNER JOIN SFAPortal.dbo.ACE_FILIAL ON (ACE_FILIAL.ID = ACE_USUARIO_COLIGADA_FILIAL.ACF_ID) WHERE AUC_ID = (SELECT AUC_ID FROM SFAPortal.dbo.ACE_USUARIO_COLIGADA WHERE ACC_ID = NVO_NOTAS_FISCAIS.NVO_EMPRESA AND USU_ID = :usuario AND AUC_ATIVO = 1 AND (NVO_NOTAS_FISCAIS.NVO_ENVIADO = 0 OR NVO_NOTAS_FISCAIS.NVO_ENVIADO = 3))) AND NVO_USUARIO = ACE_USUARIO.USU_LOGIN", nativeQuery = true)
	List<NotaFiscalInterfaceDTO> getNotasFiscaisUsuario(@Param("usuario") Integer usuario);

	@Query(value = "SELECT NVO_ID as id, NVO_EMPRESA as empresa, NVO_LOJA as loja, NVO_NUMERO_NOTA as numeroNota, NVO_DATA_EMISSAO as dataEmissao, NVO_DATA_ENTRADA as dataEntrada, NVO_DATA_UPLOAD as dataUpload, NVO_DATA_ENVIO as dataEnvio, NVO_VALOR as valor, NVO_USUARIO as usuario, NVO_CNPJ_FORNECEDOR as cnpjFornecedor, NVO_NOME_FORNECEDOR as nomeFornecedor, NVO_NOME_ARQUIVO as nomeArquivo, NVO_ENVIADO as enviado, NVO_DATA_VENCIMENTO as dataVencimento, NVO_MOTIVO_REPROVACAO as motivoReprovacao, NVO_USUARIO_APROVACAO as usuarioAprovacao FROM NVO_NOTAS_FISCAIS WHERE NVO_EMPRESA = :empresa AND NVO_LOJA = :loja AND NVO_DATA_EMISSAO BETWEEN :dataEmissaoInicial AND :dataEmissaoFinal AND NVO_DATA_ENTRADA BETWEEN :dataEntradaInicial AND :dataEntradaFinal AND NVO_DATA_ENVIO BETWEEN :dataEnvioInicial AND :dataEnvioFinal AND NVO_DATA_VENCIMENTO BETWEEN :dataVencimentoInicial AND :dataVencimentoFinal AND NVO_VALOR BETWEEN :valorMinimo AND :valorMaximo AND NVO_ENVIADO IN (:status)", nativeQuery = true)
	List<NotaFiscalInterfaceDTO> getNotasFiscais(@Param("empresa") Integer empresa, @Param("loja") Integer loja, @Param("dataVencimentoInicial") String dataVencimentoInicial, @Param("dataVencimentoFinal") String dataVencimentoFinal, @Param("dataEmissaoInicial") String dataEmissaoInicial, @Param("dataEmissaoFinal") String dataEmissaoFinal, @Param("dataEntradaInicial") String dataEntradaInicial, @Param("dataEntradaFinal") String dataEntradaFinal, @Param("dataEnvioInicial") String dataEnvioInicial, @Param("dataEnvioFinal") String dataEnvioFinal, @Param("valorMinimo") BigDecimal valorMinimo, @Param("valorMaximo") BigDecimal valorMaximo, @Param("status") List<Integer> status);

	@Query(value = "select NVO_ID as id, NVO_EMPRESA as empresa, NVO_LOJA as loja, NVO_NUMERO_NOTA as numeroNota, NVO_DATA_EMISSAO as dataEmissao, NVO_DATA_ENTRADA as dataEntrada, NVO_DATA_UPLOAD as dataUpload, NVO_DATA_ENVIO as dataEnvio, NVO_VALOR as valor, NVO_USUARIO as usuario, NVO_CNPJ_FORNECEDOR as cnpjFornecedor, NVO_NOME_FORNECEDOR as nomeFornecedor, NVO_NOME_ARQUIVO as nomeArquivo, NVO_ENVIADO as enviado, NVO_DATA_VENCIMENTO as dataVencimento, NVO_MOTIVO_REPROVACAO as motivoReprovacao, NVO_USUARIO_APROVACAO as usuarioAprovacao FROM NVO_NOTAS_FISCAIS where NVO_ID = :id", nativeQuery = true)
	NotaFiscalInterfaceDTO getNotaFiscalId(@Param("id") Integer id);
	
	@Query(value = "select EMAIL from SFAPortal.dbo.ACE_USUARIO WHERE USU_LOGIN = :usuario", nativeQuery = true)
	String getEmailUsuarioInterno(@Param("usuario") String usuario);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE NVO_NOTAS_FISCAIS SET NVO_ENVIADO = 4, NVO_USUARIO_APROVACAO = :usuario WHERE NVO_ID = :id", nativeQuery = true)
	void aprovar(@Param("id") Integer id, @Param("usuario") String usuario);

	@Modifying
	@Transactional
	@Query(value = "UPDATE NVO_NOTAS_FISCAIS SET NVO_ENVIADO = 3, NVO_MOTIVO_REPROVACAO = :motivo, NVO_USUARIO_APROVACAO = :usuario WHERE NVO_ID = :id", nativeQuery = true)
	void reprovar(@Param("id") Integer id, @Param("usuario") String usuario, @Param("motivo") String motivo);
}