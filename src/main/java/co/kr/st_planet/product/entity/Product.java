package co.kr.st_planet.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int productId; // 상품 ID
    private String productName; // 상품 이름
    private BigDecimal productPrice; // 가격
    private int stock; // 재고 수량
    private String manufacturer; // 제조사
    private String brand; // 브랜드 이름
    private String isActive; // 상품 상태
    private int categoryId; // 카테고리 ID
    private int saleId; // 판매 ID
}
