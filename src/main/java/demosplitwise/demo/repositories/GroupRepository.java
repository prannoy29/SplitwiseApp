package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group,Long> {

    List<Group> findByGroupName(String name);
    List<Group> findByCreatedBy(String name);
    Group findByGroupId(long id);
}
