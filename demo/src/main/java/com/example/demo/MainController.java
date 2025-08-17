package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "hi";
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
    public ResponseEntity<String> loginUser(JoinDTO joinDTO) {
        return ResponseEntity.ok("로그인 성공");
    }
}
