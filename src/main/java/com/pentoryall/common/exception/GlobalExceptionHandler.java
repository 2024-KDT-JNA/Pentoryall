package com.pentoryall.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public String errorView(CustomException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
//        model.addAttribute("redirectUrl", "/");
        return "/error/errorPage";
    }

//    @ExceptionHandler(Exception.class)
//    public String errorView(Exception e, Model model) {
//        model.addAttribute("errorMessage", e.getMessage());
//        return "/error/errorPage";
//    }
}
