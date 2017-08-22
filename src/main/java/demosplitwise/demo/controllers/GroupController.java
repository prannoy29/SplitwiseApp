package demosplitwise.demo.controllers;

import demosplitwise.demo.domain.Group;
import demosplitwise.demo.domain.Split;
import demosplitwise.demo.domain.User;
import demosplitwise.demo.domain.UserGroup;
import demosplitwise.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
   // @Qualifier("userGroupRepository")
    UserGroupRepository userGroupRepository;

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    UserRepository userRepository;

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

    @RequestMapping(value = "/group/findAllUsersByGroupId",method = RequestMethod.GET)
    public List<User> findAllUsersByGroupId(@RequestParam("groupId")long groupId){
        List<User> mylist = new ArrayList<>();
        for (UserGroup userGroup: userGroupRepository.findByGroupId(groupId)){
            mylist.add(userRepository.findOne(userGroup.getUid()));
        }
        return mylist;
    }
    @RequestMapping(value = "/group/addUsers",method = RequestMethod.POST)
    public void addUsers(@RequestParam("groupId") Long groupId, @RequestParam("userId") Long[] userId){
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

    @RequestMapping(value="/group/showSplits",method = RequestMethod.GET)
    public List<Split> showSplits(@RequestParam("groupId")long groupId){
        List<Split> mylist = new ArrayList<>();
        List<UserGroup>userGroupList = userGroupRepository.findByGroupIdOrderByDebt(groupId);
        int sizeOfList = userGroupList.size();
        System.out.println(sizeOfList);
        while(!userGroupList.isEmpty()||sizeOfList!=0){
           if(Math.abs(userGroupList.get(0).getDebt())> Math.abs(userGroupList.get(sizeOfList-1).getDebt())) {
               double temp = userGroupList.get(0).getDebt() + userGroupList.get(sizeOfList - 1).getDebt();
               userGroupList.get(0).setDebt(temp);
               userGroupList.get(sizeOfList - 1).setDebt(0);
               Split split = new Split();

               System.out.format("%d owes %d Rupees %f",
                       userGroupList.get(sizeOfList - 1).getUid(),
                       userGroupList.get(0).getUid(), userGroupList.get(0).getDebt());
               userGroupList.remove(sizeOfList - 1);
               sizeOfList = sizeOfList - 1;
           }
           else if (Math.abs(userGroupList.get(0).getDebt())==Math.abs(userGroupList.get(sizeOfList-1).getDebt())){

               if(!userGroupList.isEmpty()) {
                   userGroupList.remove(0);
                   sizeOfList = sizeOfList - 2;
               }
               else sizeOfList = sizeOfList - 1;
           }

           else
            {
                double temp = userGroupList.get(0).getDebt() + userGroupList.get(sizeOfList - 1).getDebt();
                userGroupList.get(0).setDebt(0);
                userGroupList.get(sizeOfList - 1).setDebt(temp);
                userGroupList.remove( 0);
                sizeOfList = sizeOfList - 1;

            }
        }
        return mylist;
    }

    @RequestMapping(value="/group/settleUp",method=RequestMethod.POST)
    public void settleUp(@RequestParam("groupId")long groupId){
        for(UserGroup userGroup:userGroupRepository.findByGroupId(groupId)){
            User userTemp = userRepository.findOne(userGroup.getUid());
            userTemp.setDebt(userRepository.findOne(userGroup.getUid()).getDebt()-
                    userGroup.getDebt());
            userRepository.save(userTemp);
            userGroup.setDebt(0);
            userGroupRepository.save(userGroup);
        }
    }

    @RequestMapping(value = "/group/groupName",method = RequestMethod.GET)
    public List<String> findByMatch(@RequestParam("s") String s){
        List<String> mylist = new ArrayList<>();
        for (Group group : groupRepo.matchedNames(s)){
            mylist.add(group.getGroupName());
        }
        return mylist;
    }

}
