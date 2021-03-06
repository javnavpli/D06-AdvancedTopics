
package acme.features.auditor.auditRecords;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditRecords.AuditRecords;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	//	@Query("select a from Job a where (select d.job.id from AuditRecords d where d.auditor.id != ?1) contains a.id ")
	//	Collection<Job> findManyMine(int id);
	//"select a from Job a where a.auditRecords.auditor.id != ?=1 "
	@Query("select a from AuditRecords a where a.auditor.userAccount.id = ?1")
	Collection<AuditRecords> findManyMine(int id);

	@Query("select j from AuditRecords j where j.id = ?1")
	AuditRecords findOneAuditById(int id);

	@Query("select a from Auditor a where a.userAccount.id = ?1")
	Auditor findAuditorById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int jobId);
}
