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

    @GetMapping("/membership/{userId}")
    public String getUserProfile(@PathVariable("userId") String userId, Model model) {
        MembershipDTO membershipDTO = userProfileService.selectMembershipUserProfileBySubscribeUserId(userId); // membershipService에서 해당 유저의 멤버십 정보 가져오기
        model.addAttribute("membershipDTO", membershipDTO); // 모델에 membershipDTO 추가
        System.out.println("subscribeUserId = " + userId);
        return "views/userProfile/membershipInfo"; // 해당 뷰 반환
    }


    @GetMapping("/storyList/{userId}")
    public String getSubscriberList(Model model, @AuthenticationPrincipal UserDTO user, @PathVariable String userId) {
        char isSubscriberVisible = user.getIsSubscriberVisible();
        if (isSubscriberVisible == 'N') {
            return "/views/userProfile/subscriberPrivate";
        } else {
            List<SubscribeDTO> subscribersList = userProfileService.selectUserSubscriberListByUserId(userId);
            System.out.println(subscribersList);
            String userName = user.getName();
            model.addAttribute("userName", userName);
            String introduction = user.getIntroduction();
            model.addAttribute("introduction", introduction);
            model.addAttribute("subscribers", subscribersList);
            if (subscribersList.isEmpty()) {
                return "/views/userProfile/noSubscribeList";
            } else {
                return "/views/userProfile/userSubscriberList";
            }
        }
    }
}