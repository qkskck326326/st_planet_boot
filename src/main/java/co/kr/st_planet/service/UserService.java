package co.kr.st_planet.service;

import co.kr.st_planet.entity.Customer;
import co.kr.st_planet.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Customer registerCustomer(Customer customer) {
        if (userMapper.findByEmail(customer.getEmail()) != null) {
            throw new RuntimeException("User with this email already exists");
        }

        userMapper.insertCustomer(customer);  // MyBatis를 통해 사용자 정보를 데이터베이스에 저장
        return customer;
    }

    public List<Customer> getAllUsers() {
        return userMapper.findAll();
    } //

    public Customer getUserByEmail(String id) {
        return userMapper.findByEmail(id);
    }

    public Customer getUserByName(String name) {
        return userMapper.findByUsername(name);
    }

}// class
