package demosplitwise.demo.repositories;

import demosplitwise.demo.domain.UserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by zemoso on 9/8/17.
 */
public interface UserGroupRepository extends CrudRepository<UserGroup,Long> {
    List<UserGroup> findByUserId(long id);
    List<UserGroup> findByGroupId(long id);
    UserGroup findByGroupIdAndUserId(long groupId, long userId);
    List<UserGroup> findByOrOrderByDebt();
}