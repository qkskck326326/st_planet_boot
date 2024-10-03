package co.kr.st_planet.security.service;

import co.kr.st_planet.security.entity.Customer;
import co.kr.st_planet.security.mapper.UserMapper;
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

    public void recordLoginLog(String email, String ip) {
        userMapper.recordLoginLog(email, ip);
    }

    public String checkLastIp(String email) {
        return userMapper.checkLastIp(email);
    }

    public void initializeCustomerIp(String email) {
        userMapper.initializeCustomerIp(email);
    }
}// class
