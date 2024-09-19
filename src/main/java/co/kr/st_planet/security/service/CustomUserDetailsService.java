package co.kr.st_planet.security.service;

import co.kr.st_planet.security.entity.Customer;
import co.kr.st_planet.security.mapper.UserMapper;
import co.kr.st_planet.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = userMapper.findByEmail(email);

        if (customer == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new CustomUserDetails(customer); // CustomUserDetails로 캐스팅
    }
}
