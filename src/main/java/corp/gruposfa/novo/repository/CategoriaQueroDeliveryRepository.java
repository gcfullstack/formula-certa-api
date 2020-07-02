package corp.gruposfa.novo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO;
import corp.gruposfa.novo.model.CategoriaQueroDelivery;
import corp.gruposfa.novo.model.dto.CategoriaQueroDeliveryDTO;

@Repository
public interface CategoriaQueroDeliveryRepository extends JpaRepository<CategoriaQueroDelivery,Integer> {
	
	@Query("select new corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO(c.codCategoriaConsinco,c.nomeCategoria) from CategoriaQueroDelivery c where c.ambiente = :ambiente")
	List<CategoriaCompareDTO> buscarRegistros(@Param("ambiente") String ambiente);
	
	@Query("select new corp.gruposfa.novo.model.dto.CategoriaQueroDeliveryDTO(c.id,c.nomeCategoria,c.codCategoriaConsinco,c.codCategoriaQueroDelivery) from CategoriaQueroDelivery c where c.codCategoriaConsinco = :codCategoria and c.ambiente = :ambiente")
	CategoriaQueroDeliveryDTO buscarRegistroPorCodCategoriaConsinco(@Param("codCategoria") Integer codCategoria,@Param("ambiente") String ambiente);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM NVO_CATEGORIA_QUERO_DELIVERY where COD_CATEGORIA_CONSINCO = :codCategoria AND AMBIENTE = :ambiente", nativeQuery = true)
	void excluirRegistroPorCodCategoriaConsinco(@Param("codCategoria") Integer codCategoria,@Param("ambiente") String ambiente);
	
}
