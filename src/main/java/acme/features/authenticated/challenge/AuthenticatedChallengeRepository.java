
package acme.features.authenticated.challenge;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.challenges.Challenge;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedChallengeRepository extends AbstractRepository {

	@Query("select c from Challenge c where c.id = ?1")
	Challenge findOneById(int id);

	@Query("select c.deadline from Challenge c where c.id = ?1")
	Date findDeadlineById(int id);

	@Query("select c from Challenge c where datediff(current_timestamp,c.deadline) < 0")
	Collection<Challenge> findManyAll();

}
