
package acme.entities.spams;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity {

	//Serialisation identifier---------------------------------

	private static final long	serialVersionUID	= 1L;

	//Atributes------------------------------------------------

	@NotBlank
	private String				englishWords;

	@NotBlank
	private String				spanishWords;

	@Digits(integer = 3, fraction = 2)
	@Range(min = 0, max = 100)
	@NotNull
	private Double				threshold;

}
