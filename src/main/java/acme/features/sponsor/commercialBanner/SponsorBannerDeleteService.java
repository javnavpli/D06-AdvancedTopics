
package acme.features.sponsor.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.CommercialBanner;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorBannerDeleteService implements AbstractDeleteService<Sponsor, CommercialBanner> {
	//Internal state --------------------------------------------------------------

	@Autowired
	private SponsorBannerRepository repository;


	//AbstractUpdateService<Administrator, Announcement> interface -------------------------

	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		int sponsorId = request.getPrincipal().getActiveRoleId();

		return this.repository.findSponsorByCommercialBannerId(request.getModel().getInteger("id")) == sponsorId;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "sponsor");
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "url", "slogan", "creditCard.holder", "creditCard.brand", "creditCard.deadline", "creditCard.number", "creditCard.cvv");
	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneBannerById(id);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		CreditCard c = entity.getCreditCard();
		this.repository.delete(entity);
		this.repository.delete(c);
	}

}
