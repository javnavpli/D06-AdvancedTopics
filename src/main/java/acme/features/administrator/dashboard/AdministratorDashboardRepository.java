/*
 * AdministratorUserAccountRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	//Applications Per Day
	@Query("select count(*) from Application a where a.status='0' and day(a.moment) = day(?1)")
	Integer pendingApplications(Date date);

	@Query("select count(*) from Application a where a.status='1' and day(a.moment) = day(?1)")
	Integer acceptedApplications(Date date);

	@Query("select count(*) from Application a where a.status='2' and day(a.moment) = day(?1)")
	Integer rejectedApplications(Date date);

	@Query("select  a.moment from Application a group by day(a.moment) having a.moment  >= (subdate(curdate(),'28/00/00')) order by moment asc")
	ArrayList<Date> applicationDates();

	//Companies and Investors
	@Query("select a.sector from CompanyRecords a group by a.sector order by a.sector asc")
	ArrayList<String> companiesSectors();

	@Query("select a.sector from InvestorRecord a group by a.sector order by a.sector asc")
	ArrayList<String> investorsSectors();

	@Query("select count(a.sector) from CompanyRecords a group by a.sector order by a.sector asc")
	ArrayList<Integer> companies();

	@Query("select count(a.sector) from InvestorRecord a group by a.sector order by a.sector asc")
	ArrayList<Integer> investors();

	//Jobs and Applications
	@Query("select  finalMode from Job group by finalMode order by finalMode asc")
	ArrayList<String> jobsStatus();

	@Query("select  status from Application group by status order by status asc")
	ArrayList<String> applicationStatus();

	@Query("select  count(finalMode) from Job group by finalMode order by finalMode asc")
	ArrayList<Integer> jobs();

	@Query("select  count(status) from Application group by status order by status asc")
	ArrayList<Integer> application();

	//Companies and Investors Data
	@Query("select count(a) from Announcement a")
	Integer numbAnn();

	@Query("select count(a) from CompanyRecords a")
	Integer numbComp();

	@Query("select count(a) from InvestorRecord a")
	Integer numbInv();

	//Employer and Worker Data
	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double jobsEmployer();

	@Query("select avg(select count(a) from Application a where a.job.id in (select j.id from Job j where j.employer.id = e.id)) from Employer e")
	Double applicationsEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double applicationsWorker();

}
