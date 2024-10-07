package co.kr.st_planet.cart.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartId implements Serializable {
    private String email;
    private String productId;

}