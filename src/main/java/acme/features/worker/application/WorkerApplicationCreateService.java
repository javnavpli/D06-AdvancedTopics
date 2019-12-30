
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.ApplicationStatus;
import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	//Internal state --------------------------------------------------------------

	@Autowired
	WorkerApplicationRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result = true;
		Principal principal = request.getPrincipal();
		int jobId = request.getModel().getInteger("id");

		Collection<Application> applications = this.repository.findManyByWorkerId(principal.getActiveRoleId());
		for (Application a : applications) {
			if (a.getJob().getId() == jobId) {
				result = false;
				break;
			}
		}

		Job job = this.repository.findJobById(jobId);
		if (job.getStatus().equals("Draft")) {
			result = false;
		}

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		if (job.getDeadline().before(moment)) {
			result = false;
		}

		return result;

	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "status", "worker", "job", "skills", "qualifications");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal principal = request.getPrincipal();
		int workerId = principal.getActiveRoleId();

		Worker worker = this.repository.findWorkerById(workerId);

		entity.setSkills(worker.getSkillsRecord());
		entity.setQualifications(worker.getQualificationsRecord());

		request.unbind(entity, model, "referenceNumber", "statement", "skills", "qualifications");

	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();

		//Status
		ApplicationStatus status = ApplicationStatus.PENDING;
		result.setStatus(status);

		//Worker
		Principal principal = request.getPrincipal();
		Worker worker = this.repository.findWorkerById(principal.getActiveRoleId());
		result.setWorker(worker);

		//Job
		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findJobById(jobId);
		result.setJob(job);

		//Moment
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		//Skills
		//		result.setSkills(worker.getSkillsRecord());

		//Qualifications
		//		result.setQualifications(worker.getQualificationsRecord());

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);
	}

}
