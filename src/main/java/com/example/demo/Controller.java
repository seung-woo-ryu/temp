package com.example.demo;

import com.example.demo.dto.OAuthLoginResponseDto;
import com.example.demo.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
@Slf4j
public class Controller {

    private final Service service;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login/returnLogin.do")
    public String getUserInfo(@RequestParam String code, @RequestParam String state, Model model) {
        OAuthLoginResponseDto accessToken = service.getAccessToken(code);
        UserProfileDto userInfo = service.getUserInfo(accessToken);

        model.addAttribute("name", userInfo.getName());
        return "main";
    }
}
