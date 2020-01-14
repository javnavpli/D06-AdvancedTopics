
package acme.entities.banner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import acme.entities.roles.Sponsor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NonCommercialBanner extends Banner {

	//Serialisation identifier ----------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	private String				jingle;

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

}
