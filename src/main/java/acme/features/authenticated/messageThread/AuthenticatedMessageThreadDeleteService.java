
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.MessageThread;
import acme.entities.userThread.UserThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedMessageThreadDeleteService implements AbstractDeleteService<Authenticated, MessageThread> {
	//Internal state --------------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		Principal p = request.getPrincipal();
		int starterId = this.repository.findStarterId(request.getModel().getInteger("id"));
		boolean res = starterId == this.repository.findAuthenticatedByPrincipal(p.getAccountId()).getId();

		return res;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result = new MessageThread();
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<MessageThread> request, final MessageThread entity) {
		assert request != null;
		assert entity != null;

		int id = request.getModel().getInteger("id");
		Collection<UserThread> userThreads = this.repository.findManyUserThread(id);
		for (UserThread ut : userThreads) {
			this.repository.delete(ut);
		}

		this.repository.delete(entity);
	}

}
