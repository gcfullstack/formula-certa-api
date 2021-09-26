package corp.formulacerta.integracao.mirror.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta;
import corp.formulacerta.integracao.mirror.model.LogConsultaFormulaCerta.OnlyLastImportedIdInterface;

@Repository
public interface LogConsultaFormulaCertaRepository extends JpaRepository<LogConsultaFormulaCerta, Integer> {

	OnlyLastImportedIdInterface findTopByOrderByIdDesc();
}
