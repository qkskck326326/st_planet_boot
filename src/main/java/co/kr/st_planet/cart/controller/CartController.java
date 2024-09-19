package co.kr.st_planet.cart.controller;

import co.kr.st_planet.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

   @Autowired
    private CartService cartService;
}
