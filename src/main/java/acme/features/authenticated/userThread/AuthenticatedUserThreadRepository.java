
package acme.features.authenticated.userThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.MessageThread;
import acme.entities.userThread.UserThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedUserThreadRepository extends AbstractRepository {

	@Query("select u from UserThread u where u.id = ?1")
	UserThread findOneById(int id);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findMessageThreadById(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByPrincipal(int id);

	@Query("select u from UserThread u where u.messageThread.id = ?1")
	Collection<UserThread> findManyAll(int authId);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int i);

	@Query("select a from Authenticated a where a.id NOT IN (select ut.user.id from UserThread ut where ut.messageThread.id = ?1)")
	Collection<Authenticated> findManyAllUsersExceptThread(int id);

	@Query("select mt.starter.id from MessageThread mt where mt.id = (select ut.messageThread.id from UserThread ut where ut.id = ?1)")
	Integer findStarterMessageThread(int id);

	@Query("select mt.starter.id from MessageThread mt where mt.id = ?1")
	Integer findStarterByMessageThreadId(int id);

	@Query("select ut.user.id from UserThread ut where ut.id = ?1")
	Integer findAuthenticatedByUTId(int i);

}
