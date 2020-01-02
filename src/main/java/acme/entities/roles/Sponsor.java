
package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.banner.NonCommercialBanner;
import acme.entities.creditCard.CreditCard;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Sponsor extends UserRole {

	private static final long						serialVersionUID	= 1L;

	@NotBlank
	private String									organisationName;

	@OneToOne(optional = true)
	@Valid
	private CreditCard								creditCard;

	@OneToMany(mappedBy = "sponsor")
	private Collection<@Valid NonCommercialBanner>	nonCommercialBanners;

}
