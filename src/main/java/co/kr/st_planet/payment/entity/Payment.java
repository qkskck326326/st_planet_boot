package co.kr.st_planet.payment.entity;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class Payment {
    private String paymentId;            // 거래의 고유식별자 (Primary Key)
    private String email;                // 사용자 이메일 (Foreign Key)
    private String couponId;             // 쿠폰 ID (Foreign Key)
    private int beforeDiscountAmount;    // 할인 전 금액
    private int finalAmount;             // 최종 결제 금액
    private Timestamp paymentTime;       // 거래 발생 일시 (DATETIME)
    private String recipientAddress;     // 받는 사람의 주소
}