
package acme.features.authenticated.userThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.userThread.UserThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedUserThreadShowService implements AbstractShowService<Authenticated, UserThread> {

	//Internal state --------------------------------------------------

	@Autowired
	private AuthenticatedUserThreadRepository repository;


	//AbstractShowService<Administrator, Announcement> interface ------

	@Override
	public boolean authorise(final Request<UserThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<UserThread> request, final UserThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int userThreadID = request.getModel().getInteger("id");
		int activeRoleID = request.getPrincipal().getActiveRoleId();
		int starter = this.repository.findStarterMessageThread(userThreadID);
		boolean removable = starter == activeRoleID;
		removable = removable && this.repository.findAuthenticatedByUTId(userThreadID) != activeRoleID;
		model.setAttribute("removable", removable);

		request.unbind(entity, model, "userUsername");
	}

	@Override
	public UserThread findOne(final Request<UserThread> request) {
		assert request != null;

		UserThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

}
