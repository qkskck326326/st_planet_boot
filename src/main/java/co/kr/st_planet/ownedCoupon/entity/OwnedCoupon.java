package co.kr.st_planet.ownedCoupon.entity;

import lombok.Data;

@Data
public class OwnedCoupon {
    private String couponId;      // 쿠폰 ID (Foreign Key, Primary Key)
    private String email;         // 사용자 이메일 (Foreign Key, Primary Key)
    private char usageStatus;     // 사용 여부 (Y/N)
}