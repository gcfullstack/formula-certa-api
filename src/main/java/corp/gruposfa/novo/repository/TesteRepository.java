package corp.gruposfa.novo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import corp.gruposfa.novo.model.EntityTest;

@Repository
public interface TesteRepository extends JpaRepository<EntityTest,Integer> {

}
