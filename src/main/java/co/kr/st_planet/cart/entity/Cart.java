package co.kr.st_planet.cart.entity;

import lombok.Data;

@Data
public class Cart {
    private String email;          // 이메일 (Foreign Key - Customer 테이블) primary key
    private String productId;      // 상품 ID (Foreign Key - Products 테이블) primary key
    private int productCount;      // 상품 개수 (Not Null)
}