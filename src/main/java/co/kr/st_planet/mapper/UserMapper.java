package co.kr.st_planet.mapper;

import co.kr.st_planet.entity.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertCustomer(Customer customer);

    List<Customer> findAll();
    Customer findByEmail(String email);
    Customer findByUsername(String username);
}
