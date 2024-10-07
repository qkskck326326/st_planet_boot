package co.kr.st_planet.security.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Customer {
    private String email;    // Primary Key
    private String password;    // Not Null
    private String provider;    // Not Null, planet/kakao
    private String name;     // Not Null
    private Date birth;      // Not Null
    private String phoneNumber; // Not Null
    private String address;  // Not Null
    private String nickname; // Not Null
    private Timestamp registerTime; // Not Null, Default: CURRENT_TIMESTAMP
    private String profileImageUrl;
    private char customerType;  // Not Null, '0' for 고객, '1' for 관리자
    private char loginOk = 'Y'; // Default: 'Y', Not Null, Y/N
    private String snsAccessToken;
    private char unsubscribeStatus; // Not Null, Default: 'N' (탈퇴 여부)
    private String lastLoginIp;
}
