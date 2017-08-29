package demosplitwise.demo.controllers;


import demosplitwise.demo.domain.User;
import demosplitwise.demo.domain.UserTransaction;
import demosplitwise.demo.repositories.UserGroupRepository;
import demosplitwise.demo.repositories.UserRepository;
import demosplitwise.demo.repositories.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @ResponseStatus(value = HttpStatus.OK, reason = "New User added")
    public void save(@RequestBody User user){repository.save(user);}

    @RequestMapping(value="/user/profile", method = RequestMethod.GET)
    public User profile(@RequestParam("userName") String userName) throws NullPointerException{
        User tempUser = repository.findByName(userName);
        if(tempUser == null){
            throw new NullPointerException("The user id does not exist");
        }
        else
        return tempUser;
    }

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

    @RequestMapping(value = "/user/findByEmailId", method= RequestMethod.GET)
    public Long emailidfind(@RequestParam("emailId") String emailId) throws NullPointerException{
        User tempUser = repository.findByEmailId(emailId);
        if(tempUser == null){
            throw new NullPointerException("The user id does not exist");
        }
        else
            return tempUser.getUserId();
    }

    @RequestMapping(value = "/user/groupExpense",method = RequestMethod.GET)
    public double groupExpense(@RequestParam("userId")long userId,
                            @RequestParam("groupId")long groupId){
       return userGroupRepository.findByGroupIdAndUserId(groupId,userId).getDebt();
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
        return repository.findOne(userId).getDebt();
    }

    @RequestMapping(value = "/user/userName", method = RequestMethod.GET)
    public List<String> findByMatch(@RequestParam("s") String s) {
        List<String> mylist = new ArrayList<>();
        for (User user : repository.matchedNames(s)){
            mylist.add(user.getName());
        }
        return mylist;

    }

    @ExceptionHandler(NullPointerException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(),"User does not exist");
    }

    @RequestMapping(value = "user/names", method = RequestMethod.GET)
    public List<String> findAllnames(){
        return repository.allNames();
    }

    @RequestMapping(value = "user/emails", method = RequestMethod.GET)
    public List<String> findAllemails(){
        return repository.allEmails();
    }

    @RequestMapping(value = "user/userId", method = RequestMethod.GET)
    public List<Long> findAllids(){ return repository.allIds(); }

}