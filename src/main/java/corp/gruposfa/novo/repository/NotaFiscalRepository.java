package corp.gruposfa.novo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import corp.gruposfa.novo.dto.NotaFiscalDTO.NotaFiscalInterfaceDTO;
import corp.gruposfa.novo.model.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Integer> {

	@Query(value = "select NVO_ID as id, NVO_EMPRESA as empresa, NVO_LOJA as loja, NVO_NUMERO_NOTA as numeroNota, NVO_DATA_EMISSAO as dataEmissao, NVO_DATA_ENTRADA as dataEntrada, NVO_DATA_UPLOAD as dataUpload, NVO_VALOR as valor, NVO_USUARIO as usuario, NVO_CNPJ_FORNECEDOR as cnpjFornecedor, NVO_NOME_FORNECEDOR as nomeFornecedor, NVO_NOME_ARQUIVO as nomeArquivo, NVO_ENVIADO as enviado FROM NVO_NOTAS_FISCAIS where NVO_EMPRESA IN (SELECT ACC_ID FROM SFAPortal.dbo.ACE_USUARIO_COLIGADA WHERE USU_ID = :usuario AND AUC_ATIVO = 1) and NVO_LOJA IN (SELECT SFAPortal.dbo.ACE_FILIAL.ACF_ID FROM SFAPortal.dbo.ACE_USUARIO_COLIGADA_FILIAL INNER JOIN SFAPortal.dbo.ACE_FILIAL ON (ACE_FILIAL.ID = ACE_USUARIO_COLIGADA_FILIAL.ACF_ID) WHERE AUC_ID = (SELECT AUC_ID FROM SFAPortal.dbo.ACE_USUARIO_COLIGADA WHERE ACC_ID = NVO_NOTAS_FISCAIS.NVO_EMPRESA AND USU_ID = :usuario AND AUC_ATIVO = 1 AND NVO_NOTAS_FISCAIS.NVO_ENVIADO = 0))", nativeQuery = true)
	List<NotaFiscalInterfaceDTO> getNotasFiscaisUsuario(@Param("usuario") Integer usuario);

}
