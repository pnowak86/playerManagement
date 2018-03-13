package playerMng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/createUser")
    public String createUser(){

        return "create-user";
    }

}
