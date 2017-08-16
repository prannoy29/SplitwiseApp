package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.Transactions;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TransRepository extends CrudRepository<Transactions,Long>{
    List<Transactions> findByGroupId(long id);
   // List<Transactions> findByGroupIdAndTransID(long groupId, long transID);
}
