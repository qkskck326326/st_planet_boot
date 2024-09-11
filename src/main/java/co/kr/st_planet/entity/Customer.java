package co.kr.st_planet.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Customer {
    private String email;    // Primary Key
    private String password;
    private String name;     // Not Null
    private Date birth;      // Not Null
    private String phoneNumber; // Not Null
    private String address;  // Not Null
    private String nickname; // Not Null
    private Timestamp registerTime; // Not Null, Default: CURRENT_TIMESTAMP
    private String profileImageUrl;
    private char loginOk;    // Default: 'Y', Not Null, Y/N
    private String snsAccessToken;
    private char unsubscribeStatus;
}
