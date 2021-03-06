
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duty.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {
	//Internal state --------------------------------------------------------------

	@Autowired
	private EmployerDutyRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		int idJob = this.repository.findOneJobByDutyId(request.getModel().getInteger("id"));
		Job j = this.repository.findOneJobById(idJob);

		return !j.getFinalMode() && j.getEmployer().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "timexWeek");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		Duty result = new Duty();
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDutyById(id);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("timexWeek")) {
			errors.state(request, entity.getTimexWeek() <= 100. && entity.getTimexWeek() > 0., "timexWeek", "employer.duty.form.error.timexWeekRestriction");
		}

	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		int idJob = this.repository.findOneJobByDutyId(request.getModel().getInteger("id"));
		Job j = this.repository.findOneJobById(idJob);
		entity.setJob(j);
		this.repository.save(entity);
	}
}
