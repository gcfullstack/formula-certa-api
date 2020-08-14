package corp.gruposfa.novo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import corp.gruposfa.novo.model.ProdutoQueroDelivery;
import corp.gruposfa.novo.model.dto.ProdutoQueroDeliveryDTO;

@Repository
public interface ProdutoQueroDeliveryRepository extends JpaRepository<ProdutoQueroDelivery,Integer> {
	
	@Query("select new corp.gruposfa.novo.model.dto.ProdutoQueroDeliveryDTO(p.id,p.codLoja,p.codProduto,p.nomeProduto,p.codBarra,p.precoVarejo,p.qtdEstoque,p.dataAlteracaoEstoque) from ProdutoQueroDelivery p where p.codLoja = :codLoja")
	List<ProdutoQueroDeliveryDTO> buscarProdutosPorCodLoja(@Param("codLoja") Integer codLoja);
	
	@Modifying
	@Transactional
	@Query(value = "update nvo_produto_quero_delivery set qtd_estoque = :estoque where cod_loja = :codLoja and cod_barra = :codBarra", nativeQuery = true)
	void atualizarEstoque(@Param("codLoja") Integer codLoja,@Param("estoque") Integer estoque,@Param("codBarra") String codBarra);

}
