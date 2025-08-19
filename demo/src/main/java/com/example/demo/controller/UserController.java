package com.example.demo.controller;

import com.example.demo.domain.user.dto.JoinDTO;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.config.jwt.JWTUtil;
import com.example.demo.config.jwt.RefreshEntity;
import com.example.demo.config.jwt.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @GetMapping("/")
    public String index() {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();

        return "Hello Main (" + name + ", role:" + role + ")";
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinUser(JoinDTO joinDTO) {
        boolean is = userService.join(joinDTO);

        if (is) {
            return ResponseEntity.ok("회원가입 성공");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser() {
        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin page";
    }

    @GetMapping("/user")
    public String user() {
        return "user page";
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {

            //response status code
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            //response status code
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {

            //response body
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(username, newRefresh, 86400000L);

        //response
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}
