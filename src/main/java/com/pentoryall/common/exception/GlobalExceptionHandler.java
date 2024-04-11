package com.pentoryall.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PageNotFoundException.class)
    public String handleNotFoundException() {
        return "error/404";
    }
//
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception e, Model model) {
//        model.addAttribute("errors", e.fillInStackTrace());
//        return "error/error";
//    }
}

