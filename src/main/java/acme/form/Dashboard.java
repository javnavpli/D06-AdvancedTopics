
package acme.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	//Serialisation Identifier------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Applications Per Day
	Map<Date, Integer>			pendingApplications;
	Map<Date, Integer>			acceptedApplications;
	Map<Date, Integer>			rejectedApplications;
	ArrayList<Date>				applicationsDates;

	//Companies and Investors
	Map<String, Integer>		companiesSectors;
	Map<String, Integer>		investorsSectors;

	//Jobs and Applications
	Map<String, Integer>		jobsStatus;
	Map<String, Integer>		applicationStatus;

	//Companies and Investors Data
	Integer						numberAnnouncements;
	Integer						numberCompanies;
	Integer						numberInvestors;

	//Employer and Worker Data
	Double						jobsPerEmployer;
	Double						applicationsPerEmployer;
	Double						applicationsPerWorker;

}
