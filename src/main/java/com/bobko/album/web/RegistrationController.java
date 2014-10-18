package com.bobko.album.web;

/**
 * Controller class that provides registration of new users
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
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

        // check empty fields
        if ((user == null) || user.getPw().isEmpty()
                || user.getUsrName().isEmpty()) {
            return "redirect:/registration?error=true";
        }

        // set default user role
        user.setRole(IUserService.ROLE_ADMIN);
        user.setActive(true);

        MessageDigest messageDigest = null;
        String hashedPass = "";

        // encode pw to md5 hash
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(user.getPw().getBytes(), 0, user.getPw()
                    .length());
            hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
            if (hashedPass.length() < 32) {
                hashedPass = "0" + hashedPass;
            }

        } catch (NoSuchAlgorithmException e) {
            return "redirect:/registration?error=true";
        }

        user.setPw(hashedPass);

        Users checkUser = null;
        checkUser = userService.getUserByName(user.getUsrName());

        // check if new user already exists in base
        if ((checkUser != null)
                && (checkUser.getUsrName().equalsIgnoreCase(user.getUsrName()))) {
            return "redirect:/registration?error=true";
        }
        try {
            userService.addUser(user);
        } catch (ConstraintViolationException ex) {
            return "redirect:/registration?error=true";
        }
        // redirect to login page on success
        return "redirect:/login";
    }
}
