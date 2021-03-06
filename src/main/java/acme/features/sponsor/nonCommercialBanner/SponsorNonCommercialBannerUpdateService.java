
package acme.features.sponsor.nonCommercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorNonCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, NonCommercialBanner> {

	//Internal state ---------------------------------------------------------

	@Autowired
	private SponsorNonCommercialBannerRepository repository;


	//AbstractUpdateService<Sponsor, CommercialBanner> interface --------------

	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		assert request != null;

		return this.repository.findOneNCBByCBId(request.getModel().getInteger("id")) == request.getPrincipal().getActiveRoleId();
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "url", "slogan", "jingle");
	}

	@Override
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {
		assert request != null;

		NonCommercialBanner result;
		result = this.repository.findOneBannerById(request.getModel().getInteger("id"));

		return result;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("slogan")) {
			double threshold = this.repository.findThreshold();
			String[] spanishWords = this.repository.findSpanishWords().split(", ");
			String[] englishWords = this.repository.findEnglishWords().split(", ");
			double numberWordsSlogan = entity.getSlogan().split("\\s|\\.|\\,").length;
			double numberWordsJingle = entity.getJingle().split("\\s|\\.|\\,").length;

			double spamWords = 0.;
			for (String s : spanishWords) {
				if (entity.getSlogan().contains(s)) {
					spamWords++;
				}
				if (entity.getJingle().contains(s)) {
					spamWords++;
				}
			}
			for (String s : englishWords) {
				if (entity.getSlogan().contains(s)) {
					spamWords++;
				}
				if (entity.getJingle().contains(s)) {
					spamWords++;
				}
			}

			errors.state(request, spamWords / (numberWordsSlogan + numberWordsJingle) * 100 <= threshold, "slogan", "sponsor.nonCommercialBanner.form.error.spam");

		}

	}

	@Override
	public void update(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
