
package acme.entities.banner;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.entities.creditCard.CreditCard;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CommercialBanner extends Banner {

	//Serialisation identifier ----------------------------

	private static final long serialVersionUID = 1L;

	//Attributes ------------------------------------------

	@NotNull
	@Valid
	@OneToOne(optional = false)
	private CreditCard creditCard;

}
