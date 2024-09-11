package co.kr.st_planet.controller;

import co.kr.st_planet.entity.Customer;
import co.kr.st_planet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestUserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<Customer> getAllUsers(){
        return userService.getAllUsers();
    }
}
