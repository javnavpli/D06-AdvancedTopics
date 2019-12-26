
package acme.features.authenticated.userThread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.MessageThread;
import acme.entities.userThread.UserThread;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedUserThreadCreateService implements AbstractCreateService<Authenticated, UserThread> {

	@Autowired
	AuthenticatedUserThreadRepository repository;


	@Override
	public boolean authorise(final Request<UserThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<UserThread> request, final UserThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//		Authenticated user;
		//
		//		int username = request.getModel().getInteger("userToAdd");
		//		user = this.repository.findAuthenticatedById(username);
		//		entity.setUser(user);

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<UserThread> request, final UserThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Authenticated> systemUsersToAdd = this.repository.findManyAllUsersExceptThread(request.getModel().getInteger("id"));
		model.setAttribute("systemUsers", systemUsersToAdd);
		model.setAttribute("userToAdd", "");

		request.unbind(entity, model);
	}

	@Override
	public UserThread instantiate(final Request<UserThread> request) {
		UserThread result;
		result = new UserThread();

		MessageThread mt = this.repository.findMessageThreadById(request.getModel().getInteger("id"));
		result.setMessageThread(mt);

		return result;
	}

	@Override
	public void validate(final Request<UserThread> request, final UserThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	//@SuppressWarnings("unchecked")
	@Override
	public void create(final Request<UserThread> request, final UserThread entity) {
		assert request != null;
		assert entity != null;

		//		MessageThread messageThread = this.repository.findMessageThreadById(request.getModel().getInteger("id"));
		//		Collection<UserThread> ut = messageThread.getUsers();

		//		Authenticated user = this.repository.findAuthenticatedById(request.getModel().getInteger("userId"));
		//
		//		entity.setMessageThread(messageThread);
		//		entity.setUser(user);

		this.repository.save(entity);

	}

}
