package co.kr.st_planet.coupon.entity;

import lombok.Data;

@Data
public class Coupon {
    private String couponId;     // 쿠폰 ID (Primary Key)
    private String couponName;   // 쿠폰명 (Foreign Key - coupon_names 테이블)
    private int discountRate;    // 할인율 (Not Null)
}
