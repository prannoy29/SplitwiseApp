package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.Group;
import demosplitwise.demo.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group,Long> {

    List<Group> findByGroupName(String name);
    List<Group> findByCreatedBy(String name);
    Group findByGroupId(long id);

    @Query("SELECT u FROM Group u where u.groupName LIKE concat(:groupName,'%') ")
    List<Group> matchedNames(@Param("groupName") String groupName);
}
