package co.kr.st_planet.cart.service;

import co.kr.st_planet.cart.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
}
