package com.example.emailclient;

import Models.SecurityFilter;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class Controller {
    SecurityFilter filter;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
 String authenticate(@RequestHeader(value="Authorization") String userInfo){
        return  filter.getInstance().authenticateLogin(userInfo);
    }

}
