
package acme.features.authenticated.offer;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.offers.Offer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedOfferRepository extends AbstractRepository {

	@Query("select a from Offer a where a.id = ?1")
	Offer findOneById(int id);

	@Query("select a.deadline from Offer a where a.id = ?1")
	Date findDeadlineById(int id);

	@Query("select a from Offer a where datediff(current_timestamp,a.deadline) < 0")
	Collection<Offer> findManyAll();

}
