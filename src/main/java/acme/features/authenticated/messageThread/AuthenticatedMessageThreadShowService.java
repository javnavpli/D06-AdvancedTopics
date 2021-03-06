
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	//Internal state --------------------------------------------------

	@Autowired
	private AuthenticatedMessageThreadRepository repository;


	//AbstractShowService<Administrator, Announcement> interface ------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		Collection<Integer> ut = this.repository.findManyUserThreadId(request.getModel().getInteger("id"));

		return ut.contains(request.getPrincipal().getActiveRoleId());
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Principal p = request.getPrincipal();
		int starterId = this.repository.findStarterId(request.getModel().getInteger("id"));
		boolean removable = starterId == p.getActiveRoleId();
		model.setAttribute("removable", removable);
		boolean canAdd = starterId == request.getPrincipal().getActiveRoleId();
		model.setAttribute("canAdd", canAdd);

		request.unbind(entity, model, "moment", "starterUsername", "title");
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

}
