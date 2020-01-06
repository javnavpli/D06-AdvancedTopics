
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerJobShowService implements AbstractShowService<Employer, Job> {

	//Internal state --------------------------------------------------

	@Autowired
	private EmployerJobRepository repository;


	//AbstractShowService<Administrator, Announcement> interface ------

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		Job job = this.repository.findOneJobById(request.getModel().getInteger("id"));

		return job.getEmployer().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int jobId = request.getModel().getInteger("id");

		boolean removable = this.repository.findApplicationsByJobId(jobId).isEmpty();
		model.setAttribute("removable", removable);

		boolean upgradeable = !this.repository.findOneJobById(jobId).getFinalMode();
		model.setAttribute("upgradeable", upgradeable);

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "moreInfo", "description", "finalMode", "XXX1.text", "XXX1.XXX2");

	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;

	}

}
