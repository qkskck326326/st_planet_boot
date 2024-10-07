package co.kr.st_planet.security.mapper;

import co.kr.st_planet.security.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertCustomer(Customer customer);

    List<Customer> findAll();
    Customer findByEmail(String email);
    Customer findByUsername(String username);

    void recordLoginLog(String email, String ip);

    String checkLastIp(String email);

    void initializeCustomerIp(String email);
}
