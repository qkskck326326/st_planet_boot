package co.kr.st_planet.security;

import co.kr.st_planet.security.entity.FirstLoginTokenSet;
import co.kr.st_planet.security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/login")
    public ResponseEntity<FirstLoginTokenSet> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        try {
            // 이메일과 비밀번호 유효성 검사
            if (email == null || email.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot be empty");
            }
            if (password == null || password.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be empty");
            }

            // 인증 처리
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // 인증 성공 시 JWT 생성
            String accessToken = "Bearer " + jwtTokenProvider.createToken(authentication);
            String refreshToken = "Bearer " + jwtTokenProvider.createRefreshToken(authentication);

            FirstLoginTokenSet tokenSet = new FirstLoginTokenSet(accessToken, refreshToken);

            // 응답으로 JWT 반환
            return ResponseEntity.ok(tokenSet);

        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password", e);
        }
    }

    @PostMapping("/reissue")
    public ResponseEntity<Map<String, String>> reissue(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(Map.of("error", "Invalid token format"), HttpStatus.BAD_REQUEST);
        }

        try {
            String refreshToken = authorizationHeader.substring(7);

            if (!jwtTokenProvider.validateToken(refreshToken)) {
                return new ResponseEntity<>(Map.of("error", "Refresh token is invalid or expired"), HttpStatus.FORBIDDEN);
            }

            String newAccessToken = jwtTokenProvider.createToken(refreshToken);

            // 응답 바디에 액세스 토큰을 포함
            Map<String, String> responseBody = Map.of(
                    "accessToken", newAccessToken
            );

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "An error occurred while issuing new access token"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/me")
    public UserDetails getCurrentUser(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        String email = jwtTokenProvider.getUserEmailFromToken(token);
        return customUserDetailsService.loadUserByUsername(email);
    }
}
