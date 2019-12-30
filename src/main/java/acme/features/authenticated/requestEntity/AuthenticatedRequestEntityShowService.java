
package acme.features.authenticated.requestEntity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestEntity.RequestEntity;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedRequestEntityShowService implements AbstractShowService<Authenticated, RequestEntity> {

	//Internal state --------------------------------------------------

	@Autowired
	private AuthenticatedRequestEntityRepository repository;


	//AbstractShowService<Administrator, RequestEntity> interface ------

	@Override
	public boolean authorise(final Request<RequestEntity> request) {
		assert request != null;

		Date currentDate = new Date(System.currentTimeMillis());
		Date deadline = this.repository.findDeadlineById(request.getModel().getInteger("id"));

		return currentDate.before(deadline);
	}

	@Override
	public void unbind(final Request<RequestEntity> request, final RequestEntity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "deadline", "text", "reward", "ticker");
	}

	@Override
	public RequestEntity findOne(final Request<RequestEntity> request) {
		assert request != null;

		RequestEntity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;

	}

}
