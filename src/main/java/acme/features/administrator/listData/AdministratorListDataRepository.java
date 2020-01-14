
package acme.features.administrator.listData;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorListDataRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Integer numbAnn();

	@Query("select count(a) from CompanyRecords a")
	Integer numbComp();

	@Query("select count(a) from InvestorRecord a")
	Integer numbInv();

}
