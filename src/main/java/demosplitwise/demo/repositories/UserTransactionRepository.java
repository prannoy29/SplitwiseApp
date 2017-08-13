package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.UserTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTransactionRepository extends CrudRepository<UserTransaction,Long> {

    List<UserTransaction> findByUserId(long id);
}
