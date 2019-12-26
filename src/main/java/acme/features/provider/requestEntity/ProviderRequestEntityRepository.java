
package acme.features.provider.requestEntity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestEntityRepository extends AbstractRepository {

	@Query("select ticker from RequestEntity r")
	Collection<String> findAllTickers();

}
