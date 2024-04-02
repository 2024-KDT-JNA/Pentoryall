package com.pentoryall.subscribe.controller;

import com.pentoryall.membership.dto.MembershipJoinDTO;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.service.SubscribeService;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subscribe")
public class SubscribeController {

    private final SubscribeService subscribeService;

    public SubscribeController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }

    @PostMapping("/add")
    public String addSubscribe(@ModelAttribute SubscribeDTO subscribeDTO) {
        subscribeService.addSubscribe(subscribeDTO);
        return "redirect:/subscribe/storyList"; // 예시로 storyList 페이지로 리다이렉트
    }

    @PostMapping("/cancel")
    public String cancelSubscribe(@ModelAttribute SubscribeDTO subscribeDTO) {
        subscribeService.cancelSubscribe(subscribeDTO);
        return "redirect:/subscribe/storyList"; // 예시로 storyList 페이지로 리다이렉트
    }

    @GetMapping("/list")
    public String getSubscriberList(Model model, MembershipJoinDTO membershipJoinDTO, @AuthenticationPrincipal UserDTO user) {
        if (user == null) {
            return "redirect:/user/login";
        } else {
            String userName = user.getName();
            model.addAttribute("userName", userName);
            List<SubscribeDTO> subscribersList = subscribeService.selectAllSubscribers(user.getCode());
            System.out.println(subscribersList);
            if (subscribersList.isEmpty()) {
                return "/views/subscribe/noSubscriberList";
            } else {
                model.addAttribute("subscribers", subscribersList);
            }return "/views/subscribe/subscriberList";
        }
    }


    /* 페이지 연결 관련 매핑*/
    @GetMapping("/noList")
    public String getNoSubscriberList(){
        return "/views/subscribe/noSubscriberList";
    }

    @GetMapping("/storyList")
    public String getStoryList() {
        return "/views/subscribe/storyList";
    }

    @GetMapping("/postList")
    public String getPostList() {
        return "/views/subscribe/postList";
    }
}
















