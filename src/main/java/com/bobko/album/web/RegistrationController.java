package com.bobko.album.web;

/**
 * Controller class that provides registration of new users
 * @author oleksii bobko
 * @data 12.08.2013
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bobko.album.domain.UserEntity;
import com.bobko.album.service.interfaces.IUserService;

@Controller
public class RegistrationController {

    @Autowired
    private IUserService userService;

    /**
     * redirect to registration page and put to Map UserEntity object
     * */
    @RequestMapping(value = "/registration" , method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new UserEntity());
        return "registration";
    }
    
}
