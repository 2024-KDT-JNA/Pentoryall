package com.pentoryall.subscribe.controller;

import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.service.SubscribeService;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subscribe")
public class SubscribeController {

    private SubscribeService subscriberService;


    public SubscribeController(SubscribeService subscriberService) {
        this.subscriberService = subscriberService;
    }



    @GetMapping("/qweqe")
    public void addSubscribe(@RequestBody SubscribeDTO subscribeDTO) {

        subscriberService.addSubscribe(subscribeDTO);

    }

    @PostMapping("/")
    public void cancelSubscribe(@RequestBody SubscribeDTO subscribeDTO) {
        subscriberService.addSubscribe(subscribeDTO);
    }



/* 페이지 연결 관련 매핑*/

    @GetMapping("/storyList")
    public String getStoryList() {
        return "/views/subscribe/storyList";
    }
    @GetMapping("/postList")
    public String getPostList(){
        return"/views/subscribe/postList";
    }
    @GetMapping("/story/subscriber/list")
    public String getScribeList(){
        return "/views/subscribe/subscriberList";
    }
















}