package com.pentoryall.story.controller;

import com.pentoryall.common.exception.PageNotFoundException;
import com.pentoryall.story.dto.StoryUserDTO;
import com.pentoryall.user.dto.UserDTO;
import com.pentoryall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class StoryController {

    private final UserService userService;

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
}

