
package acme.entities.banner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Sponsor;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NonCommercialBanner extends DomainEntity {

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

	private String				jingle;

	@Valid
	@ManyToOne(optional = true)
	private Sponsor				sponsor;

}
