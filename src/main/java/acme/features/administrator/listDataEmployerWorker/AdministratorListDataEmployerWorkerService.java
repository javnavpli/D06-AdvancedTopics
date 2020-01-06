
package acme.features.administrator.listDataEmployerWorker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.form.ListDataEmployerWorker;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorListDataEmployerWorkerService implements AbstractShowService<Administrator, ListDataEmployerWorker> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorListDataEmployerWorkerRepository repository;


	//AbstractListService<Authenticated, Announcement> interface ------

	@Override
	public boolean authorise(final Request<ListDataEmployerWorker> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<ListDataEmployerWorker> request, final ListDataEmployerWorker entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "jobsPerEmployer", "applicationsPerEmployer", "applicationsPerWorker", "jobsWithXXX1", "XXX1WithXXX3", "XXX4WithPassword");

	}

	@Override
	public ListDataEmployerWorker findOne(final Request<ListDataEmployerWorker> request) {
		assert request != null;

		ListDataEmployerWorker result = new ListDataEmployerWorker();

		result.setJobsPerEmployer(this.repository.jobsEmployer());
		result.setApplicationsPerEmployer(this.repository.applicationsEmployer());
		result.setApplicationsPerWorker(this.repository.applicationsWorker());
		result.setJobsWithXXX1(0.);
		result.setXXX1WithXXX3(0.);
		result.setXXX4WithPassword(0.);
		if (this.repository.jobsWithXXX1() != null) {
			result.setJobsWithXXX1(this.repository.jobsWithXXX1() * 100);
		}
		if (this.repository.XXX1WithXXX3() != null) {
			result.setXXX1WithXXX3(this.repository.XXX1WithXXX3() * 100);
		}
		if (this.repository.applicationsWithXXX4Protected() != null) {
			result.setXXX4WithPassword(this.repository.applicationsWithXXX4Protected() * 100);
		}

		return result;
	}

}
