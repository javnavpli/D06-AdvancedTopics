
package acme.features.administrator.listData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.form.ListData;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorListDataService implements AbstractShowService<Administrator, ListData> {

	//Internal state --------------------------------------------------

	@Autowired
	AdministratorListDataRepository repository;


	//AbstractListService<Authenticated, Announcement> interface ------

	@Override
	public boolean authorise(final Request<ListData> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<ListData> request, final ListData entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "numberAnnouncements", "numberCompanies", "numberInvestors");

	}

	@Override
	public ListData findOne(final Request<ListData> request) {
		assert request != null;

		ListData result = new ListData();

		result.setNumberAnnouncements(this.repository.numbAnn());
		result.setNumberCompanies(this.repository.numbComp());
		result.setNumberInvestors(this.repository.numbInv());

		return result;
	}

}
