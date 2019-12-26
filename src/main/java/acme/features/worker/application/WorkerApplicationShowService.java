
package acme.features.worker.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Worker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class WorkerApplicationShowService implements AbstractShowService<Worker, Application> {

	//Internal state --------------------------------------------------

	@Autowired
	private WorkerApplicationRepository repository;


	//AbstractShowService<Administrator, Announcement> interface ------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		return this.repository.findOneApplicationById(request.getModel().getInteger("id")).getWorker().getId() == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "moment", "statement", "skills", "qualifications", "status", "job.title");

	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		return this.repository.findOneApplicationById(request.getModel().getInteger("id"));

	}

}
