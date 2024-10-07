package co.kr.st_planet.security;

import co.kr.st_planet.security.entity.FirstLoginTokenSet;
import co.kr.st_planet.security.getUserInfo.GetUserInfo;
import co.kr.st_planet.security.service.CustomUserDetailsService;
import co.kr.st_planet.security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private UserService userService;

    private final GetUserInfo getUserInfo = new GetUserInfo();

    @PostMapping("/login")
    public ResponseEntity<FirstLoginTokenSet> login(@RequestBody Map<String, String> loginData, HttpServletRequest request) {
        String email = loginData.get("email");
        String password = loginData.get("password");
        String ip = getUserInfo.getClientIp(request);
        String lastLoginIp = null;

        try {
            // 이메일과 비밀번호 유효성 검사
            if (email == null || email.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이메일을 입력해 주세요");
            }
            if (password == null || password.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호를 입력해 주세요");
            }

            // 인증 처리
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            lastLoginIp = userService.checkLastIp(email);
            if (!(lastLoginIp == null || lastLoginIp.equals(ip))) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "IP address mismatch");
            }
            userService.recordLoginLog(email, ip);
            // 인증 성공 시 JWT 생성
            String accessToken = "Bearer " + jwtTokenProvider.createToken(authentication);
            String refreshToken = "Bearer " + jwtTokenProvider.createRefreshToken(authentication);

            FirstLoginTokenSet tokenSet = new FirstLoginTokenSet(accessToken, refreshToken);

            // 응답으로 JWT 반환
            return ResponseEntity.ok(tokenSet);

        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일과 비밀번호가 일치하지 않습니다.", e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(HttpHeaders.AUTHORIZATION) String Authorization) {
        String token = null;
        if (Authorization.startsWith("Bearer ")) {
            token = Authorization.substring(7);
        }

        String email = jwtTokenProvider.getUserEmailFromToken(token);

        try{
            userService.initializeCustomerIp(email);
        } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "UnexpectError", e);
        }

        return ResponseEntity.ok("successLogout");
    }

    @PostMapping("/reissue")
    public ResponseEntity<Map<String, String>> reissue(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                       HttpServletRequest request) {
        String requestIp = getUserInfo.getClientIp(request);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(Map.of("error", "Invalid token format"), HttpStatus.BAD_REQUEST);
        }

        try {
            String refreshToken = authorizationHeader.substring(7);

            if (!jwtTokenProvider.validateToken(refreshToken)) {
                // 403 + 에러메세지 "InvalidRefreshToken"
                return new ResponseEntity<>(Map.of("error", "InvalidRefreshToken"), HttpStatus.FORBIDDEN);
            }

            //////// 마지막 로그인 아이피 확인, 불일치시 특정 오류 반환
            String email = jwtTokenProvider.getUserEmailFromToken(refreshToken);
            String lastIp = userService.checkLastIp(email);

            if (!(requestIp.equals(lastIp) || lastIp == null)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "ip_error");
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

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                       HttpServletRequest request) {
        String requestIp = getUserInfo.getClientIp(request);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(Map.of("error", "Invalid token format"), HttpStatus.BAD_REQUEST);
        }

        try {
            String refreshToken = authorizationHeader.substring(7);

            if (!jwtTokenProvider.validateToken(refreshToken)) {
                return new ResponseEntity<>(Map.of("error", "Refresh token is invalid or expired"), HttpStatus.FORBIDDEN);
            }

            //////// 마지막 로그인 아이피 확인, 불일치시 특정 오류 반환
            String email = jwtTokenProvider.getUserEmailFromToken(refreshToken);
            String lastIp = userService.checkLastIp(email);

            if (!(requestIp.equals(lastIp) || lastIp != null)){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "ip_error");
            }

            String newRefreshToken = jwtTokenProvider.createNewRefreshToken(refreshToken);

            // 응답 바디에 새로운 리프레쉬토큰 추가
            Map<String, String> responseBody = Map.of(
                    "newRefreshToken", newRefreshToken
            );

            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
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
