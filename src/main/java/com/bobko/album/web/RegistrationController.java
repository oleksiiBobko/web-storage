package com.bobko.album.web;

/**
 * Controller class that provides registration of new users
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String registration(Map<String, Object> map) {
        map.put("user", new Users());
        return "registrationPage";
    }

    /**
     * perform adding new user to db
     * */
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute("user") Users user) {

        try {
            userService.addUser(user);
        } catch (Exception ex) {
            return "redirect:/registration?error=true";
        }
        // redirect to login page on success
        return "redirect:/login";
    }
}
