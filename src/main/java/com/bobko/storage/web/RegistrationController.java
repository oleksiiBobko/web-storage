package com.bobko.storage.web;

/**
 * Controller class that provides registration of new users
 * @author oleksii bobko
 * @data 12.08.2013
 */

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bobko.storage.domain.UserEntity;
import com.bobko.storage.exceptions.TokenExpiredException;
import com.bobko.storage.exceptions.TokenVerifyedException;
import com.bobko.storage.exceptions.UserNotFoundException;
import com.bobko.storage.service.interfaces.IUserService;

@Controller
public class RegistrationController {

    @Autowired
    private IUserService userService;
    private static final String NOT_ACTIVE = "notActive";
    private static final String OK = "ok";
    private static final Logger LOG = LogManager.getLogger(RegistrationController.class);

    /**
     * redirect to registration page and put to Map UserEntity object
     * */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            /* The user is logged in :) */
            return "redirect:/";
        }
        
        model.addAttribute("user", new UserEntity());
        return "registration";
    }

    @RequestMapping(value = "/registration/{token}", method = RequestMethod.GET)
    public String confirmRegistration
          (HttpServletRequest request, @PathVariable String token, Model model) {
        UserEntity user = null;
        try {
            user = userService.getUserByToken(token, true);
        } catch (TokenExpiredException e) {
            LOG.warn("token expired " + user);
            model.addAttribute("message", "token.expired");
            return "redirect:/reset_password";
        } catch (TokenVerifyedException e) {
            LOG.warn("token already verified " + user);
            model.addAttribute("message", "token.verified");
            return "redirect:/reset_password";
        } catch (UserNotFoundException e) {
            LOG.warn("user not found " + token);
            return "redirect:/";
        }
  
        model.addAttribute("user", user.getLogin());
        model.addAttribute("token", token);
        LOG.warn("user activated successfully " + user);
        return "redirect:/thankyou";
        
    }
    
    @RequestMapping(value = "/thankyou", method = RequestMethod.GET)
    public String thankYou(@ModelAttribute("user") String user,
            @ModelAttribute("token") String token, Model model) {
        UserEntity userEntity = userService.getUserByName(user);
        if (userEntity != null) {
            if (!userEntity.getToken().getToken().equals(token)) {
                model.addAttribute("code", NOT_ACTIVE);
            } else if (userEntity.isActive()) {
                model.addAttribute("code", OK);
            } else {
                model.addAttribute("code", NOT_ACTIVE);
            }
        } else {
            model.addAttribute("code", NOT_ACTIVE);
        }
        
        model.addAttribute("user", user);
        
        return "thankyou";
    } 
    
    @RequestMapping(value = "/reset_password/{token}", method = RequestMethod.GET)
    public String resetPassword
          (HttpServletRequest request, @PathVariable String token, Model model) {
        UserEntity user = null;
        try {
            user = userService.getUserByToken(token, false);
        } catch (Exception e) {
            LOG.warn("some error during password reset" + e.getMessage());
            return "redirect:/";
        }
        
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities("PASSWORD_UPDATE"));
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        model.addAttribute("user", new UserEntity());
        return "redirect:/new_password";
    }
    
    @RequestMapping(value = "/new_password" , method = RequestMethod.GET)
    @PreAuthorize("hasRole('PASSWORD_UPDATE')")
    public String newPassword(Model model) {
        model.addAttribute("user", new UserEntity());
        return "new_password";
    }
    
}
