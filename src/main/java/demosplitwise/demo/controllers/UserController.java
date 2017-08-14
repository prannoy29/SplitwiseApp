package demosplitwise.demo.controllers;


import demosplitwise.demo.domain.User;
import demosplitwise.demo.repositories.UserGroupRepository;
import demosplitwise.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserRepository repository;

    @Autowired
    UserGroupRepository userGroupRepository;


    @RequestMapping(value ="/user/save", method= RequestMethod.POST)
    public void save( @RequestBody User user){
        repository.save(user);
    }

    @RequestMapping(value = "/user/update", method= RequestMethod.PUT)
    public String update(@RequestBody User user){
        repository.save(user);
        return "done";
    }

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
}
