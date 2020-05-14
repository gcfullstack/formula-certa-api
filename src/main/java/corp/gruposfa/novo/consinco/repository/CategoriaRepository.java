package corp.gruposfa.novo.consinco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import corp.gruposfa.novo.model.NotaFiscal;

@Repository
public interface CategoriaRepository extends JpaRepository<NotaFiscal,Integer>{
	
	@Query(value = "SELECT DISTINCT NOME_CATEGORIA FROM BI_V_DADOS_QUERODELIVERY where cod_loja = 2", nativeQuery = true)
	List<String> buscarNomeCategorias();
	
}
