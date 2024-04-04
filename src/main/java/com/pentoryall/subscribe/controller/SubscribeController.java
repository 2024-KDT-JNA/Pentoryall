package com.pentoryall.subscribe.controller;

import com.pentoryall.membership.dto.MembershipJoinDTO;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.service.SubscribeService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
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
    public ResponseEntity<Void> addSubscribe(@ModelAttribute SubscribeDTO subscribeDTO) {
        subscribeDTO.setSubscribedDate(LocalDate.now());
        subscribeService.addSubscriber(subscribeDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cancel")
    public ResponseEntity<Void> cancelSubscribe(@RequestBody SubscribeDTO subscribeDTO) {
        subscribeService.cancelSubscribe(subscribeDTO);
        System.out.println(subscribeDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/list")
    public String getSubscriberList(Model model, MembershipJoinDTO membershipJoinDTO, @AuthenticationPrincipal UserDTO user) {


        List<SubscribeDTO> subscribersList = subscribeService.selectAllSubscribers(user.getCode());
        System.out.println(subscribersList);

        int subscriberCount = subscribeService.getSubscriberCount(user.getCode());
        model.addAttribute("subscriberCount", subscriberCount);
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

        int subscriberStoryCount = subscribeService.getSubscribeStoryCount(user.getCode());
        model.addAttribute("subscriberStoryCount", subscriberStoryCount);

        String userName = user.getName();
        model.addAttribute("userName", userName);

        String introduction = user.getIntroduction();
        model.addAttribute("introduction", introduction);
        model.addAttribute("subscribers", subscribersList);

        if (subscribersList.isEmpty()) {
            return "/views/subscribe/noStoryList";
        } else {
            return "/views/subscribe/storyList";
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

