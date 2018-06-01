package com.victoria.fargutu.unibook.web.exception;

import com.victoria.fargutu.unibook.service.commons.ExceptionType;
import com.victoria.fargutu.unibook.service.exceptions.InvalidCredentialsException;
import com.victoria.fargutu.unibook.service.exceptions.UnauthorizedException;
import com.victoria.fargutu.unibook.web.FlashMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice("com.victoria.fargutu.unibook.web")
public class ControllerExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public String handleException(UnauthorizedException exception, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (exception.isOfType(ExceptionType.AUTHORIZATION_REQUIRED) && !request.getServletPath().equals("/")) {
            redirectAttributes.addFlashAttribute("notification", FlashMessage.error(exception.getMessage()));
        }
        return "redirect:/authorization/login";
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public String handleException(InvalidCredentialsException exception, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (exception.isOfType(ExceptionType.AUTHORIZATION_REQUIRED)) {
            redirectAttributes.addFlashAttribute("notification", FlashMessage.error(exception.getMessage()));
        }
        return "redirect:/authorization/login";
    }

}
