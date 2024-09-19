package co.kr.st_planet.returnProducts.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ReturnProducts {
    private String productId;         // 상품 ID (Foreign Key, Primary Key)
    private String paymentId;         // 결제 ID (Foreign Key, Primary Key)
    private String reasonId;          // 사유 ID (Foreign Key)
    private String content;           // 상세 내용
    private char kind;                // 교환/반품 여부 (0: 교환, 1: 반품)
    private Timestamp time;           // 요청 시간 (DATETIME)
}