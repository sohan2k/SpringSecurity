package io.spring.springSecurity.Controller;

import io.spring.springSecurity.Domain.User;
import io.spring.springSecurity.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUserById(@PathVariable String id){
        return userService.getUserById(Integer.parseInt(id));
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/user/id")
    public void deleteUser(String id){
        userService.deleteUserById(Integer.parseInt(id));
    }

}
