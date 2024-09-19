package co.kr.st_planet.security.entity;

import lombok.Data;

@Data
public class FirstLoginTokenSet {
    private String accessToken;
    private String refreshToken;

    public FirstLoginTokenSet(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
