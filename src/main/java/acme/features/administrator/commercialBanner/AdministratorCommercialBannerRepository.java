
package acme.features.administrator.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banner.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCommercialBannerRepository extends AbstractRepository {

	@Query("select c from CommercialBanner c where c.id = ?1")
	CommercialBanner findOneCommercialBannerById(int id);

	@Query("select c from CommercialBanner c where c.sponsor.id is null")
	Collection<CommercialBanner> findManyAll();

	@Query("select a from Sponsor a where a.creditCard.id = ?1")
	Sponsor findOneSponsorByCreditCardId(int id);

}
