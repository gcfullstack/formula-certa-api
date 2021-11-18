package corp.formulacerta.integracao.mirror.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta;
import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta.LastImportedDataCadastroInterface;

@Repository
public interface LogConsultaFormulaCertaRepository extends JpaRepository<LogConsultaFormulaCerta, Integer> {

	LastImportedDataCadastroInterface findTopByOrderByIdDesc();
	
	@Query(value = "select LAST_DT_CADASTRO from " + 
			"LOG_CONSULTA_FORMULA_CERTA " + 
			"order by id desc " + 
			"limit 1", nativeQuery = true)
	Date findTopByOrderByIdDesc2();
	
}
