package com.bobko.album.web;

/**
 * Controller class that provides registration of new users
 * @author oleksii bobko
 * @data 12.08.2013
 */

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bobko.album.domain.Users;
import com.bobko.album.service.interfaces.IUserService;
//import javax.validation.Valid;

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
        return "registration";
    }  
    
    /**
     * perform adding new user to db
     * */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid @ModelAttribute("user") Users user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration-page";
        }
        try {
            userService.addUser(user);
        } catch (Exception ex) {
            return "registration";
        }
        return "login";
    }
}
