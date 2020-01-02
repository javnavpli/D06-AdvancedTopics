
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.MessageThread;
import acme.entities.userThread.UserThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findOneById(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByPrincipal(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select mt from MessageThread mt where mt.id in (select u.messageThread.id from UserThread u where u.user.userAccount.id = ?1)")
	Collection<MessageThread> findManyAll(int authId);

	@Query("select mt.starter.id from MessageThread mt where mt.id = ?1")
	int findStarterId(int id);

	@Query("select ut from UserThread ut where ut.messageThread.id = ?1")
	Collection<UserThread> findManyUserThread(int authId);

	@Query("select ut.user.id from UserThread ut where ut.messageThread.id = ?1")
	Collection<Integer> findManyUserThreadId(int mtId);

}
