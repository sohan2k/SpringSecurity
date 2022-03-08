package io.spring.springSecurity.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "<h1> welcome to Spring Security Home</h1>";
    }

    @GetMapping("/user")
    public String getUser(){
        return "<h1> welcome to User Page</h1>";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "<h1> welcome to Admin Page</h1>";
    }
}
