package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User u where u.name LIKE concat(:username,'%') ")
    List<User> matchedNames(@Param("username") String username);

    @Query("SELECT u.userId FROM User u")
    List<Long> allIds();

    @Query("SELECT name FROM User u")
    List<String> allNames();

    @Query("SELECT emailId FROM User u")
    List<String> allEmails();

    User findByName(String name);
    User findByEmailId(String emailId);

}
