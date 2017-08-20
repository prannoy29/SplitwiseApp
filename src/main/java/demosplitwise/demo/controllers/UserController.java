package demosplitwise.demo.controllers;


import demosplitwise.demo.domain.User;
import demosplitwise.demo.domain.UserGroup;
import demosplitwise.demo.domain.UserTransaction;
import demosplitwise.demo.repositories.UserGroupRepository;
import demosplitwise.demo.repositories.UserRepository;
import demosplitwise.demo.repositories.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Autowired
    UserGroupRepository userGroupRepository;


    @RequestMapping(value ="/user", method= RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK, reason = "My status changed")
    public void save(@RequestBody User user){repository.save(user);}

    @RequestMapping(value = "/user", method= RequestMethod.PUT)
    public void update(@RequestBody User user){repository.save(user);}

    @RequestMapping(value = "/user/findAll", method= RequestMethod.GET)
    public List<User> findAll(){
        List<User> results = new ArrayList<>();

        for(User user : repository.findAll()){
            results.add(user);
        }

        return results ;
    }

    @RequestMapping(value = "/user/findById", method= RequestMethod.GET)
    public User findById(@RequestParam("id") long id){
        return repository.findOne(id);
    }

    @RequestMapping(value = "/user/groupExpense",method = RequestMethod.GET)
    public double groupExpense(@RequestParam("userId")long userId,
                            @RequestParam("groupId")long groupId){
        double debt = 0;
        for (UserTransaction userTransaction : userTransactionRepository.findByGroupIdAndUserId(groupId,userId)){
            debt = debt + userTransaction.getPartialAmount();
        }
        UserGroup userGroup = userGroupRepository.findByGroupIdAndUserId(groupId,userId);
        userGroup.setDebt(debt);
        userGroupRepository.save(userGroup);
        return debt;
    }

    @RequestMapping(value = "/user/nonGroupExpense",method = RequestMethod.GET)
    public double nonGroupExpense(@RequestParam("userId")long userId){
        double debt = 0;
        for (UserTransaction userTransaction : userTransactionRepository.findByGroupIdAndUserId(-1,userId)){
            debt = debt + userTransaction.getPartialAmount();
        }
        return debt;
    }

    @RequestMapping(value = "/user/totalExpense",method = RequestMethod.GET)
    public double totalExpense(@RequestParam("userId")long userId){
        double debt =0;
        for(UserTransaction userTransaction:userTransactionRepository.findByUserId(userId)){
            debt = debt + userTransaction.getPartialAmount();
        }
        User user = findById(userId);
        user.setDebt(debt);
        repository.save(user);
        return debt;

    }

    @RequestMapping(value = "/user/userName", method = RequestMethod.GET)
    public List<String> findByMatch(@RequestParam("s") String s) {
        List<String> mylist = new ArrayList<>();
        for (User user : repository.matchedNames(s)){
            mylist.add(user.getName());
        }
        return mylist;

    }
    }