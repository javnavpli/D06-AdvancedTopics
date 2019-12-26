
package acme.entities.banner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import acme.entities.creditCard.CreditCard;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends DomainEntity {

	//Serialisation identifier ----------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	@URL
	@NotBlank
	private String				picture;

	@URL
	@NotBlank
	private String				url;

	@NotBlank
	private String				slogan;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private CreditCard			creditCard;

}
