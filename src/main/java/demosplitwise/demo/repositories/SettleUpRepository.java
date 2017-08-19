package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.SettleUp;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SettleUpRepository extends CrudRepository<SettleUp,Long> {

    List<SettleUp> deleteSettleUpByUserIdCreditorEquals(long userIdCreditor);
}
