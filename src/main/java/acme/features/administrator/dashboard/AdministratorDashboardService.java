
package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.form.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardService implements AbstractShowService<Administrator, Dashboard> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorDashboardRepository repository;


	//AbstractListService<Anonymous, CompanyRecords> interface ------

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		boolean result;

		Principal p = request.getPrincipal();
		result = p.hasRole(Administrator.class);

		return result;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard formObject, final Model model) {
		assert request != null;
		assert formObject != null;
		assert model != null;

		//Applications Per Day
		request.unbind(formObject, model, "pendingApplications", "acceptedApplications", "rejectedApplications", "applicationsDates");

		//Companies and Investors
		request.unbind(formObject, model, "companiesSectors", "investorsSectors");

		//Jobs and Applications
		request.unbind(formObject, model, "jobsStatus", "applicationStatus");

		//Companies and Investors Data
		request.unbind(formObject, model, "numberAnnouncements", "numberCompanies", "numberInvestors");

		//Employer and Worker Data
		request.unbind(formObject, model, "jobsPerEmployer", "applicationsPerEmployer", "applicationsPerWorker");

	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		//Applications Per Day
		Map<Date, Integer> mapPending = new HashMap<Date, Integer>();
		Map<Date, Integer> mapAccepted = new HashMap<Date, Integer>();
		Map<Date, Integer> mapRejected = new HashMap<Date, Integer>();

		for (Date date : this.repository.applicationDates()) {
			mapPending.put(date, this.repository.pendingApplications(date));
		}

		for (Date date : this.repository.applicationDates()) {
			mapAccepted.put(date, this.repository.acceptedApplications(date));
		}

		for (Date date : this.repository.applicationDates()) {
			mapRejected.put(date, this.repository.rejectedApplications(date));
		}

		ArrayList<Date> applicationDates = this.repository.applicationDates();
		Collections.sort(applicationDates);

		result.setPendingApplications(mapPending);
		result.setAcceptedApplications(mapAccepted);
		result.setRejectedApplications(mapRejected);
		result.setApplicationsDates(applicationDates);

		//Companies and Investors
		Map<String, Integer> mapCompanies = new HashMap<String, Integer>();
		Map<String, Integer> mapSectors = new HashMap<String, Integer>();

		for (int i = 0; i < this.repository.companies().size(); i++) {
			mapCompanies.put(this.repository.companiesSectors().get(i), this.repository.companies().get(i));
		}

		for (int i = 0; i < this.repository.investors().size(); i++) {
			mapSectors.put(this.repository.investorsSectors().get(i), this.repository.investors().get(i));
		}

		for (int i = 0; i < this.repository.investors().size(); i++) {
			if (!mapCompanies.containsKey(this.repository.investorsSectors().get(i))) {
				mapCompanies.put(this.repository.investorsSectors().get(i), 0);
			}

		}

		for (int i = 0; i < this.repository.companies().size(); i++) {
			if (!mapSectors.containsKey(this.repository.companiesSectors().get(i))) {
				mapSectors.put(this.repository.companiesSectors().get(i), 0);
			}

		}

		result.setCompaniesSectors(mapCompanies);
		result.setInvestorsSectors(mapSectors);

		//Jobs and Applications
		Map<String, Integer> mapJobs = new HashMap<String, Integer>();
		Map<String, Integer> mapApplication = new HashMap<String, Integer>();

		for (int i = 0; i < this.repository.jobs().size(); i++) {
			mapJobs.put(this.repository.jobsStatus().get(i), this.repository.jobs().get(i));
		}

		for (int i = 0; i < this.repository.application().size(); i++) {
			mapApplication.put(this.repository.applicationStatus().get(i), this.repository.application().get(i));
		}

		result.setJobsStatus(mapJobs);
		result.setApplicationStatus(mapApplication);

		//Companies and Investors Data
		result.setNumberAnnouncements(this.repository.numbAnn());
		result.setNumberCompanies(this.repository.numbComp());
		result.setNumberInvestors(this.repository.numbInv());

		//Employer and Worker Data
		result.setJobsPerEmployer(this.repository.jobsEmployer());
		result.setApplicationsPerEmployer(this.repository.applicationsEmployer());
		result.setApplicationsPerWorker(this.repository.applicationsWorker());

		return result;
	}

}
