package demosplitwise.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
    @GetMapping("/")
    public String homepage(){
        return "index6";
    }

    /*@GetMapping("/")
    public String page(){
        return "hello";
    }*/
}