package com.pentoryall.story.controller;

import com.pentoryall.common.exception.PageNotFoundException;
import com.pentoryall.post.dto.PostDTO;
import com.pentoryall.post.service.PostService;
import com.pentoryall.series.dto.SeriesDTO;
import com.pentoryall.series.service.SeriesService;
import com.pentoryall.story.dto.StoryUserDTO;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Controller
@RequiredArgsConstructor
public class StoryController {

    private final UserService userService;
    private final PostService postService;
    private final SeriesService seriesService;

    @GetMapping("/story")
    public String storyPage(@AuthenticationPrincipal UserDTO sessionUser) {
        if (sessionUser == null)
            throw new PageNotFoundException();
        return "redirect:/" + sessionUser.getUserId();
    }

    @GetMapping("/{userId}")
    public String storyPage(@PathVariable("userId") String userId, Model model) {
        
        UserDTO selectedUser = userService.selectByUserId(userId);
        if (selectedUser == null) {
            throw new PageNotFoundException();
        }

        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));

        return "/views/story/home";
    }

    @GetMapping("/{userId}/posts")
    public String storyPostsPage(@PathVariable("userId")String userId,
                                 Model model){
        UserDTO selectedUser = userService.selectByUserId(userId);
        if (selectedUser == null) {
            throw new PageNotFoundException();
        }
        List<PostDTO> postList = postService.selectPostByUserCode(selectedUser.getCode());
        Collections.reverse(postList);
        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));
        model.addAttribute("postList",postList);
        return "/views/story/posts";
    }

    @GetMapping("/{userId}/series")
    public String storySeriesPage(@PathVariable("userId")String userId,
                                 Model model){
        UserDTO selectedUser = userService.selectByUserId(userId);
        if (selectedUser == null) {
            throw new PageNotFoundException();
        }
        List<SeriesDTO> seriesList = seriesService.getSeriesList(selectedUser.getCode());
        Collections.reverse(seriesList);
        System.out.println("seriesList = " + seriesList);
        model.addAttribute("storyUser", new StoryUserDTO(selectedUser));
        model.addAttribute("seriesList",seriesList);
        return "/views/story/series";
    }
}

