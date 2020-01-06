
package acme.features.worker.application;

import java.util.Collection;
import java.util.Date;
import java.util.List;

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

		request.bind(entity, errors, "moment", "status", "worker", "job");

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "statement", "skills", "qualifications", "answer", "XXX4", "password");

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

		result.setSkills(worker.getSkillsRecord());
		result.setQualifications(worker.getQualificationsRecord());

		//Job
		int jobId = request.getModel().getInteger("id");
		Job job = this.repository.findJobById(jobId);
		result.setJob(job);

		//Moment
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		//Reference
		String reference = job.getReference() + ":WORX";
		result.setReferenceNumber(reference);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int idJob = request.getModel().getInteger("id");
		boolean hasXXX1 = this.repository.findXXX1ByJobId(idJob) != null;

		if (!hasXXX1) {
			errors.state(request, entity.getAnswer().isEmpty() && entity.getXXX4().isEmpty() && entity.getPassword().isEmpty(), "cantComplete", "worker.application.form.error.cantComplete");
		}

		if (!errors.hasErrors("referenceNumber")) {
			List<String> referenceCodes = this.repository.findReferences();
			errors.state(request, !referenceCodes.contains(entity.getReferenceNumber()), "referenceNumber", "worker.application.form.error.reference");
		}

		if (hasXXX1) {
			if (!entity.getPassword().isEmpty()) {
				errors.state(request, !entity.getAnswer().isEmpty() && !entity.getXXX4().isEmpty(), "password", "worker.application.form.error.passNoXXX4");
			}
			if (!entity.getXXX4().isEmpty()) {
				errors.state(request, !entity.getAnswer().isEmpty(), "XXX4", "worker.application.form.error.XXX4NoAnswer");
			}
		}
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);
	}

}
