package demosplitwise.demo.controllers;

import demosplitwise.demo.domain.Group;
import demosplitwise.demo.domain.UserGroup;
import demosplitwise.demo.repositories.GroupRepository;
import demosplitwise.demo.repositories.UserGroupRepository;
import demosplitwise.demo.repositories.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Group findById(@RequestParam("id")long id){
        return groupRepo.findOne(id);
    }

    @RequestMapping(value="/group/findAllByUserId",method = RequestMethod.GET)
    public List<Group> findAllByUserId(@RequestParam("id")long id){
        List<Group> mylist = new ArrayList<>();
        for(UserGroup userGroup: userGroupRepository.findByUserId(id)){
            mylist.add(groupRepo.findByGroupId(userGroup.getGid()));
        }

        return mylist;
    }


}
