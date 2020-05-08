package corp.gruposfa.novo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import corp.gruposfa.novo.model.LogIntegracaoQueroDelivery;

@Repository
public interface LogIntegracaoQueroDeliveryRepository extends JpaRepository<LogIntegracaoQueroDelivery,Integer>{
	
}
