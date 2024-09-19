package co.kr.st_planet.returnImages.entity;

import lombok.Data;

@Data
public class ReturnImages {
    private String productId;         // 상품 ID (Foreign Key, Primary Key)
    private String paymentId;         // 결제 ID (Foreign Key, Primary Key)
    private String imagesUrl;         // 이미지 URL
}