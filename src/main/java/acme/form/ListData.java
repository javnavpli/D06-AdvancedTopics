
package acme.form;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListData implements Serializable {

	//Serialisation Identifier------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------

	Integer						numberAnnouncements;
	Integer						numberCompanies;
	Integer						numberInvestors;
	Double						minRewardRequest;
	Double						maxRewardRequest;
	Double						averageRewardRequest;
	Double						derivationRewardRequest;
	Double						minRewardOffer;
	Double						maxRewardOffer;
	Double						averageRewardOffer;
	Double						derivationRewardOffer;

}
