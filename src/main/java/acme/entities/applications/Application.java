
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.datatypes.ApplicationStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "referenceNumber")
})

public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 15)
	private String				referenceNumber;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotNull
	private ApplicationStatus	status;

	@NotBlank
	private String				statement;

	@NotBlank
	private String				skills;

	@NotBlank
	private String				qualifications;

	private String				justification;

	private String				answer;

	private String				XXX4;

	@Pattern(regexp = "^((?=(.*\\d){4,})(?=(.*[a-zA-Z]){4,})(?=(.*[¿?¡!'\"_.,;:\\-\\[\\]\\*\\/]){4,}).{8,}|)$")
	private String				password;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

}
