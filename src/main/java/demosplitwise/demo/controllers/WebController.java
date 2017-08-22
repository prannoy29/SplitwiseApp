package demosplitwise.demo.controllers;
import demosplitwise.demo.domain.Transactions;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import demosplitwise.demo.domain.Transactions;
import demosplitwise.demo.repositories.TransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class WebController {
    @GetMapping("/transaction/save")
    public String homepage() {
        return "transactionform";
    }

   /* @GetMapping("/user/profile")
    public String userloginpg() {
        return "loginpg";
    }
*/
    @GetMapping("/user")
    public String userpage() {
        return "index6";
    }

    @GetMapping("/group/save")
    public String grouppage() {
        return "newGrroup";
    }

    @GetMapping("/group/addUsers")
    public String groupAddUser() {
        return "groupAddUsers";
    }

    @GetMapping("/showDebtbyUserId")
    public String groupDebtbyUserID() {
        return "getDebt";
    }

    @GetMapping("/")
    public String homepagee() {
        return "homep";
    }
}
