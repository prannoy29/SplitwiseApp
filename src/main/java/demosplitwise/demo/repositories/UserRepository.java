package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT name FROM User u where u.name LIKE concat(:username,'%') ")
    List<String> matchedNames(@Param("username") String username);

}
