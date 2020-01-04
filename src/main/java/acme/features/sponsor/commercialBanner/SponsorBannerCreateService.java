
package acme.features.sponsor.commercialBanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banner.CommercialBanner;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	//Internal state -----------------------------------

	@Autowired
	private SponsorBannerRepository repository;


	//AbstractCreateService<Sponsoor, Banner> interface --------

	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
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

		int sponsorId = request.getPrincipal().getActiveRoleId();
		Sponsor sponsor = this.repository.findOneSponsorById(sponsorId);
		boolean cantCreate = sponsor.getCreditCard() == null;
		model.setAttribute("cantCreate", cantCreate);

		request.unbind(entity, model, "picture", "url", "slogan", "creditCard.holder", "creditCard.brand", "creditCard.deadline", "creditCard.number", "creditCard.cvv");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		int sponsorId = request.getPrincipal().getActiveRoleId();
		Sponsor sponsor = this.repository.findOneSponsorById(sponsorId);

		result = new CommercialBanner();
		if (sponsor.getCreditCard() != null) {
			CreditCard creditCard = new CreditCard();
			creditCard.setBrand(sponsor.getCreditCard().getBrand());
			creditCard.setHolder(sponsor.getCreditCard().getHolder());
			creditCard.setDeadline(sponsor.getCreditCard().getDeadline());
			creditCard.setNumber(sponsor.getCreditCard().getNumber());
			creditCard.setCvv(sponsor.getCreditCard().getCvv());
			result.setCreditCard(creditCard);
		}
		result.setSponsor(sponsor);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("slogan")) {
			double threshold = this.repository.findThreshold();
			String[] spanishWords = this.repository.findSpanishWords().split(", ");
			String[] englishWords = this.repository.findEnglishWords().split(", ");
			double numberWordsBody = entity.getSlogan().split("\\s|\\.|\\,").length;

			double spamWords = 0.;
			for (String s : spanishWords) {
				if (entity.getSlogan().contains(s)) {
					spamWords++;
				}
			}
			for (String s : englishWords) {
				if (entity.getSlogan().contains(s)) {
					spamWords++;
				}
			}

			errors.state(request, spamWords / numberWordsBody * 100 <= threshold, "slogan", "sponsor.banner.form.error.spam");

		}

		if (!errors.hasErrors("creditCard.deadline")) {
			errors.state(request, request.getModel().getString("creditCard.deadline") != null, "creditCard.deadline", "sponsor.banner.form.error.deadlineIncorrect");
		}

		if (!errors.hasErrors("creditCard.deadline")) {
			errors.state(request, request.getModel().getString("creditCard.deadline").matches("^(0[1-9]|1[0-2])\\/[0-9][0-9]$"), "creditCard.deadline", "sponsor.banner.form.error.deadlinePattern");
		}

		if (!errors.hasErrors("creditCard.deadline")) {
			Date currentDate = new Date(System.currentTimeMillis());

			String[] monthYear = request.getModel().getString("creditCard.deadline").split("/");
			String deadlineString = monthYear[0] + "/20" + monthYear[1];
			Date deadline = new Date();

			try {
				deadline = new SimpleDateFormat("MM/yyyy").parse(deadlineString);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(deadline);

			calendar.add(Calendar.HOUR, 1);

			deadline = calendar.getTime();

			errors.state(request, deadline.after(currentDate), "creditCard.deadline", "sponsor.banner.form.error.deadline");

		}
		if (!errors.hasErrors("creditCard.number")) {
			errors.state(request, request.getModel().getString("creditCard.number").matches("5[1-5][0-9]{14}$"), "creditCard.number", "sponsor.banner.form.error.numberPattern");
		}
		if (!errors.hasErrors("creditCard.cvv")) {
			errors.state(request, request.getModel().getString("creditCard.cvv").matches("^\\d{3,4}$"), "creditCard.cvv", "sponsor.banner.form.error.cvvPattern");
		}

	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		int sponsorId = request.getPrincipal().getActiveRoleId();
		if (this.repository.findCreditCardBySponsor(sponsorId).getBrand().equals(entity.getCreditCard().getBrand()) && this.repository.findCreditCardBySponsor(sponsorId).getCvv().equals(entity.getCreditCard().getCvv())
			&& this.repository.findCreditCardBySponsor(sponsorId).getDeadline().equals(entity.getCreditCard().getDeadline()) && this.repository.findCreditCardBySponsor(sponsorId).getHolder().equals(entity.getCreditCard().getHolder())
			&& this.repository.findCreditCardBySponsor(sponsorId).getNumber().equals(entity.getCreditCard().getNumber())) {
			entity.setCreditCard(this.repository.findCreditCardBySponsor(request.getPrincipal().getActiveRoleId()));

		} else {
			this.repository.save(entity.getCreditCard());
		}
		this.repository.save(entity);
	}

}
