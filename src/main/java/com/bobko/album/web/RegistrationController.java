package com.bobko.album.web;

/**
 * Controller class that provides registration of new users
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.util.Map;

//import javax.validation.Valid;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bobko.album.domain.Users;
import com.bobko.album.service.interfaces.IUserService;

@Controller
public class RegistrationController {

    @Autowired
    private IUserService userService;

    /**
     * redirect to registration page and put to Map UserEntity object
     * */
    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new Users());
        return "registration-page";
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registerSpitter(@Valid Spitter spitter, BindingResult bindingResult, Model model) {
//            if(bindingResult.hasErrors()) {
//                return "registerNewUser";
//            }
//            
//            //Check whether the entered username is available.
//            if(!spitterServiceImpl.isUsernameAvailable(spitter.getUsername())) {
//                bindingResult.addError(new FieldError(bindingResult.getObjectName(), "username", "Specified username is already taken."));
//                return "registerNewUser";
//            }
//            spitterServiceImpl.addSpitter(spitter);
//            model.addAttribute("username", spitter.getUsername());
//
//            return "registrationCompleted";
//    }
    
    
    /**
     * perform adding new user to db
     * */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid Users user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration-page";
        }
        try {
            userService.addUser(user);
        } catch (Exception ex) {
            return "registration-page";
        }
        return "redirect:login";
    }
}
