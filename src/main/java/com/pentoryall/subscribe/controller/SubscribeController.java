package com.pentoryall.subscribe.controller;

import com.pentoryall.membership.dto.MembershipJoinDTO;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.service.SubscribeService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/subscribe")
public class SubscribeController {
    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addSubscribe(@AuthenticationPrincipal UserDTO user,
                                             @ModelAttribute SubscribeDTO subscribe) {


        subscribe.setUserCode(user.getCode());
        subscribe.setSubscribeDate(LocalDate.now());
        subscribeService.addSubscriber(subscribe);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelSubscribe(@AuthenticationPrincipal UserDTO user,
                                                @ModelAttribute SubscribeDTO subscribe) {

        subscribe.setUserCode(user.getCode());
        subscribeService.cancelSubscribe(subscribe);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/list")
    public String getSubscriberList(Model model, MembershipJoinDTO membershipJoinDTO, @AuthenticationPrincipal UserDTO user) {


        List<SubscribeDTO> subscribersList = subscribeService.selectAllSubscribers(user.getCode());
        System.out.println(subscribersList);

        model.addAttribute("subscribers", subscribersList);

        if (subscribersList.isEmpty()) {
            return "/views/subscribe/noSubscriberList";
        } else {
            return "/views/subscribe/subscriberList";

        }
    }

    /* 페이지 연결 관련 매핑*/
    @GetMapping("/noList")
    public String getNoSubscriberList() {
        return "/views/subscribe/noSubscriberList";
    }

    @GetMapping("/storyList")
    public String getStoryList(Model model, @AuthenticationPrincipal UserDTO user) {

        List<SubscribeDTO> subscribersList = subscribeService.selectAllSubscribeStory(user.getCode());
        System.out.println(subscribersList);

        String userName = user.getName();
        model.addAttribute("userName", userName);
        String userId = user.getUserId();
        model.addAttribute("user", userId);

        String introduction = user.getIntroduction();
        model.addAttribute("introduction", introduction);
        model.addAttribute("subscribers", subscribersList);

        if (subscribersList.isEmpty()) {
            return "/views/subscribe/noStoryList";
        } else {
            return "/views/subscribe/storyList";
        }
    }

    @PostMapping("updateSubscriberVisible")
    public ResponseEntity<Void> updateSubscriberVisible(@RequestParam("userCode") long userCode,
                                                        @RequestParam("isSubscriberVisible") char isSubscriberVisible) {
        try {
            subscribeService.updateSubscriberVisible(userCode, isSubscriberVisible);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/noStoryList")
    public String getNoStoryList() {
        return "/views/subscribe/noStoryList";
    }


    @GetMapping("/postList")
    public String getPostList() {
        return "/views/subscribe/postList";
    }


}

