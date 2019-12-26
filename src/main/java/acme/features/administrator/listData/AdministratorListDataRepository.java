
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

	@Query("select min(a.reward.amount) from RequestEntity a where a.deadline >= NOW()")
	Double minRequestReward();

	@Query("select max(a.reward.amount) from RequestEntity a where a.deadline >= NOW()")
	Double maxRequestReward();

	@Query("select avg(a.reward.amount) from RequestEntity a where a.deadline >= NOW()")
	Double avgRequestReward();

	@Query("select stddev(a.reward.amount) from RequestEntity a where a.deadline >= NOW()")
	Double stddevRequestReward();

	@Query("select min(a.minMoney.amount) from Offer a where a.deadline >= NOW()")
	Double minRequestOffer();

	@Query("select max(a.maxMoney.amount) from Offer a where a.deadline >= NOW()")
	Double maxRequestOffer();

	@Query("select avg((a.minMoney.amount+a.maxMoney.amount)/2) from Offer a where a.deadline >= NOW()")
	Double avgRequestOffer();

	@Query("select stddev((a.minMoney.amount+a.maxMoney.amount)/2) from Offer a where a.deadline >= NOW()")
	Double stddevRequestOffer();

}
