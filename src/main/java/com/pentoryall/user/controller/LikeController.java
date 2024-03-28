package com.pentoryall.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/views/user")
public class LikeController {
    @GetMapping("/likePage")
    public String likePage() {
        return "views/user/likePage";
    }
}
