package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
