package co.kr.st_planet.paymentDetail.entity;

import lombok.Data;

@Data
public class PaymentDetail {
    private String paymentId;          // 거래의 고유식별자 (Foreign Key, Primary Key)
    private String productId;          // 상품 ID (Foreign Key, Primary Key)
    private int productCount;          // 상품 개수
    private char paymentStatus;        // 결제 상태 (Y/N)
    private int paymentAmount;         // 결제 금액
    private char deliveryStatus;       // 배송 상태 (0: 미출고, 1: 출고완료, 2: 반품확인중, 3: 환불완료, 4: 교환출고완료)
    private String trackingNumber;     // 송장 번호
}