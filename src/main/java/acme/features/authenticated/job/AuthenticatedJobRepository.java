
package acme.features.authenticated.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1 and (j.finalMode = 1 and j.deadline >= NOW())")
	Job findOneJobById(int id);

	@Query("select j from Job j where (j.finalMode = 1 and j.deadline >= NOW())")
	Collection<Job> findManyJobActive();

	@Query("select a.id from Application a where a.worker.id = (select w.id from Worker w where w.userAccount.id = ?1) and a.job.id = ?2")
	Collection<Integer> findApplicationByPrincipalId(int principalId, int jobId);

}
