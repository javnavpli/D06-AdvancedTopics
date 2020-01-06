
package acme.features.administrator.listDataEmployerWorker;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorListDataEmployerWorkerRepository extends AbstractRepository {

	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double jobsEmployer();

	@Query("select avg(select count(a) from Application a where a.job.id in (select j.id from Job j where j.employer.id = e.id)) from Employer e")
	Double applicationsEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double applicationsWorker();

	@Query("select avg(select count(jo) from Job jo where jo.id = j.id and jo.XXX1 <> '') from Job j")
	Double jobsWithXXX1();

	@Query("select avg(select count(x2) from XXX1 x2 where x2.id = x1.id and x2.XXX2 <> '') from XXX1 x1")
	Double XXX1WithXXX3();

	@Query("select avg(select count(ap) from Application ap where ap.id = a.id and ap.password <> '') from Application a")
	Double applicationsWithXXX4Protected();

}
