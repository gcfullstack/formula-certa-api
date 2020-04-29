package corp.gruposfa.novo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import corp.gruposfa.novo.model.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal,Integer> {

	/*@Query(value = "SELECT DISTINCT TOP 50 TPRODUTO.IDPRD as idProduto,TPRODUTO.CODIGOPRD as codProduto,TPRODUTO.NOMEFANTASIA as nomeFantasia,TPRODUTO.DESCRICAO as descricao, ISNULL(PRAZO_ENTREGA,0) AS prazoEntrega FROM TPRODUTO LEFT JOIN TPRDCOMPL ON (TPRODUTO.IDPRD = TPRDCOMPL.IDPRD) WHERE (NOMEFANTASIA LIKE :produto OR TPRODUTO.IDPRD LIKE :produto) AND INATIVO = 0 AND ULTIMONIVEL = 1 AND CODCOLPRD = 0 ORDER BY NOMEFANTASIA ASC", nativeQuery = true)
	List<ModeloProdutoInterfaceDTO> getProdutosPorNome(@Param("produto") String produto);*/

}
