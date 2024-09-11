package co.kr.st_planet.controller;

import co.kr.st_planet.entity.Customer;
import co.kr.st_planet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/register")
    public String register(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        try {
            Customer newUser = userService.registerCustomer(customer);
            return "User registered successfully: " + newUser.getEmail();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public List<Customer> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/getById")
    public Customer getUserById(String id){
        return userService.getUserByEmail(id);
    }

    @GetMapping("/getByName")
    public Customer getUserByName(String name){
        return userService.getUserByName(name);
    }

}
