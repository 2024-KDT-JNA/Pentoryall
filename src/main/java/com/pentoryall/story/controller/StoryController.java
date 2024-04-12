package com.pentoryall.story.controller;

import com.pentoryall.common.exception.PageNotFoundException;
import com.pentoryall.membership.dto.MembershipDTO;
import com.pentoryall.membership.service.MembershipService;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import com.pentoryall.story.dto.StoryUserDTO;
import com.pentoryall.subscribe.dto.SubscribeDTO;
import com.pentoryall.subscribe.service.SubscribeService;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/story")
public class StoryController {

    private final UserService userService;
    private final PostService postService;
    private final SeriesService seriesService;
    private final MembershipService membershipService;
    private final SubscribeService subscribeService;


    @GetMapping
    public String storyPage(@AuthenticationPrincipal UserDTO sessionUser) {
        if (sessionUser == null)
            throw new PageNotFoundException();
        return "redirect:/story/" + sessionUser.getUserId();
    }

    @GetMapping("/{userId}")
    public String storyPage(@PathVariable("userId") String userId, Model model) {

        UserDTO selectedUser = getUserOrNotFoundException(userId);
        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));

        List<PostDTO> postList = postService.selectPostByUserCode(selectedUser.getCode());
        if (!postList.isEmpty()) {
            Collections.reverse(postList);
            model.addAttribute("post", postList.get(0));
        }

        List<SeriesDTO> seriesList = seriesService.getSeriesList(selectedUser.getCode());
        if (!seriesList.isEmpty()) {
            Collections.reverse(seriesList);
            model.addAttribute("series", seriesList.get(0));
        }
        model.addAttribute("TAB_MENU", "home");
        return "/views/story/home";
    }

    @GetMapping("/{userId}/posts")
    public String storyPostsPage(@PathVariable("userId") String userId, Model model) {
        UserDTO selectedUser = getUserOrNotFoundException(userId);
        List<PostDTO> postList = postService.selectPostByUserCode(selectedUser.getCode());
        Collections.reverse(postList);
        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));
        model.addAttribute("postList", postList);

        model.addAttribute("TAB_MENU", "posts");
        return "/views/story/posts";
    }

    @GetMapping("/{userId}/series")
    public String storySeriesPage(@PathVariable("userId") String userId, Model model) {
        UserDTO selectedUser = getUserOrNotFoundException(userId);
        List<SeriesDTO> seriesList = seriesService.getSeriesList(selectedUser.getCode());
        Collections.reverse(seriesList);
        System.out.println("seriesList = " + seriesList);
        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));
        model.addAttribute("seriesList", seriesList);

        model.addAttribute("TAB_MENU", "series");
        return "/views/story/series";
    }

    @GetMapping("/{userId}/memberships")
    public String storyMembershipPage(@PathVariable("userId") String userId,
                                      @AuthenticationPrincipal UserDTO userDTO,

                                      Model model) {
        UserDTO selectedUser = getUserOrNotFoundException(userId);
        MembershipDTO membership = membershipService.selectMembershipByUserCode(selectedUser.getCode()); // 멤버십 정보를 가져옵니다.
        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));
        model.addAttribute("membership", membership); // 가져온 멤버십 정보를 모델에 추가합니다.
        model.addAttribute("loginUser", userDTO);
        model.addAttribute("user", userId);
        model.addAttribute("TAB_MENU", "membership");

        if (membership != null) {
            return "views/membership/planInfo";
        } else {
            return "views/membership/membershipInfo"; // 멤버십이 없는 경우에 대한 처리
        }
//        model.addAttribute("errorMessage", "멤버십 정보를 불러오는 중에 오류가 발생했습니다: " + e.getMessage());
//        return "views/membership/errorPage";


    }

    @GetMapping("/{userId}/subscribers")
    public String getSubscriberList(@PathVariable("userId") String userId, Model model, @AuthenticationPrincipal UserDTO userDTO) {
        UserDTO selectedUser = getUserOrNotFoundException(userId);
        char isSubscriberVisible = userDTO.getIsSubscriberVisible();
        List<SubscribeDTO> subscribersList = subscribeService.selectAllSubscribers(selectedUser.getCode());
        model.addAttribute("isSubscriber", isSubscriberVisible);
        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));
        model.addAttribute("subscribers", subscribersList);
        model.addAttribute("loginUser", userDTO);
        model.addAttribute("user", userId);
        model.addAttribute("TAB_MENU", "subscribe");
        if (subscribersList.isEmpty()) {
            return "/views/subscribe/noSubscriberList";
        } else {
            return "/views/subscribe/subscriberList";

        }
    }


    private UserDTO getUserOrNotFoundException(String userId) {
        UserDTO selectedUser = userService.selectByUserId(userId);
        if (selectedUser == null) {
            throw new PageNotFoundException();
        } else
            return selectedUser;
    }
}

