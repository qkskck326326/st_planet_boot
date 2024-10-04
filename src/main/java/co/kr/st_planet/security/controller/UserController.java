package co.kr.st_planet.security.controller;

import co.kr.st_planet.security.entity.Customer;
import co.kr.st_planet.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> register(@RequestBody Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setProvider("planet");
        customer.setLoginOk('Y');
        customer.setCustomerType('0');
        System.out.println(customer);
        try {
            Customer newUser = userService.registerCustomer(customer);
            return ResponseEntity.ok("User registered successfully: " + newUser.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to register user: " + e.getMessage());
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
