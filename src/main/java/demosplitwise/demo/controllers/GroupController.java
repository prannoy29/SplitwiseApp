package demosplitwise.demo.controllers;

import demosplitwise.demo.domain.Group;
import demosplitwise.demo.domain.UserGroup;
import demosplitwise.demo.repositories.GroupRepository;
import demosplitwise.demo.repositories.UserGroupRepository;
import demosplitwise.demo.repositories.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class GroupController {

    @Autowired
    GroupRepository groupRepo;

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @RequestMapping(value = "/group/save", method = RequestMethod.POST)
    public void register(@RequestBody Group group){
        groupRepo.save(group);
    }

    @RequestMapping(value="/group/update",method = RequestMethod.PUT)
    public void update(@RequestBody Group group){
        groupRepo.save(group);
    }

    @RequestMapping(value = "/group/findAll", method = RequestMethod.GET)
    public List<Group> findAll(){
        List<Group> mylist = new ArrayList<>();
        for(Group group : groupRepo.findAll()){
            mylist.add(group);
        }

        return mylist;
    }

    @RequestMapping(value="/group/findById", method = RequestMethod.GET)
    public Group findById(@RequestParam("groupId")long id){
        return groupRepo.findOne(id);
    }

    @RequestMapping(value="/group/findAllByUserId",method = RequestMethod.GET)
    public List<Group> findAllByUserId(@RequestParam("userId")long userId){
        List<Group> mylist = new ArrayList<>();
        for(UserGroup userGroup: userGroupRepository.findByUserId(userId)){
            mylist.add(groupRepo.findByGroupId(userGroup.getGid()));
        }

        return mylist;
    }

    @RequestMapping(value = "/group/addUsers",method = RequestMethod.POST)
    public void addUsers(Long groupId, Long[] userId){
        Date today = new Date();
        for (long i: userId) {
            UserGroup x = new UserGroup(groupId,i,today,0);
            userGroupRepository.save(x);
        }
        Group group = findById(groupId);
        int members = group.getTotalMembers();
        group.setTotalMembers(members+userId.length);
        update(group);
    }


}
