package corp.gruposfa.novo.consinco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import corp.gruposfa.novo.consinco.model.dto.CategoriaCompareDTO.CategoriaCompareInterfaceDTO;
import corp.gruposfa.novo.model.NotaFiscal;

@Repository
public interface CategoriaRepository extends JpaRepository<NotaFiscal,Integer>{
	
	@Query(value = "SELECT DISTINCT NOME_CATEGORIA as nomeCategoria,COD_CATEGORIA as codCategoria FROM BI_V_DADOS_QUERODELIVERY where cod_loja = :idLoja", nativeQuery = true)
	List<CategoriaCompareInterfaceDTO> buscarNomeCategorias(@Param("idLoja") Integer idLoja);
	
}
