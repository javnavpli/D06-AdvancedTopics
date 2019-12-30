
package acme.features.authenticated.requestEntity;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestEntity.RequestEntity;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestEntityRepository extends AbstractRepository {

	@Query("select r from RequestEntity r where r.id = ?1")
	RequestEntity findOneById(int id);

	@Query("select r.deadline from RequestEntity r where r.id = ?1")
	Date findDeadlineById(int id);

	@Query("select r from RequestEntity r where datediff(current_timestamp,r.deadline) < 0")
	Collection<RequestEntity> findManyAll();

}
