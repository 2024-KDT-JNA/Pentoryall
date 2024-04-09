package com.pentoryall.membership.userProfile.controller;

import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.userProfile.service.UserProfileService;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping("/membership/{subscribeUserCode}")
    public String getUserProfile(@PathVariable("subscribeUserCode") long subscribeUserCode, Model model) {
        MembershipDTO membershipDTO = userProfileService.selectMembershipUserProfileBySubscribeUserCode(subscribeUserCode); // membershipService에서 해당 유저의 멤버십 정보 가져오기
        model.addAttribute("membershipDTO", membershipDTO); // 모델에 membershipDTO 추가
        System.out.println("subscribeUserCode = " + subscribeUserCode);
        return "views/userProfile/membershipInfo"; // 해당 뷰 반환
    }

    @GetMapping("/header/{subscribeUserCode}")
    public String headerBySubscribeCode(@PathVariable("subscribeUserCode") long subscribeUserCode, Model model) {
        MembershipDTO membershipDTO = userProfileService.selectMembershipUserProfileBySubscribeUserCode(subscribeUserCode); // membershipService에서 해당 유저의 멤버십 정보 가져오기
        model.addAttribute("membershipDTO", membershipDTO); // 모델에 membershipDTO 추가
        return "views/userProfile/storyHeader";
    }

    @GetMapping("/storyList/{subscribeUserCode}")
    public String getSubscriberList(Model model, @AuthenticationPrincipal UserDTO user, @PathVariable long subscribeUserCode) {

        List<SubscribeDTO> subscribersList = userProfileService.selectUserSubscriberList(subscribeUserCode);
        System.out.println(subscribersList);

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
}