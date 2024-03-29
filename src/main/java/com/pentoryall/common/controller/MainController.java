package com.pentoryall.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"/", "/main", "/index"})
    public String defaultLocation() {

        return "views/index";
    }

    @RequestMapping("/story")
    public String storyPage() {

        return "/views/story/home";
    }
}
