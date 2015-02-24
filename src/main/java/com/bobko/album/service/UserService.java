package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserService
 */

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.base.IGenericDao;
import com.bobko.album.domain.Users;
import com.bobko.album.service.interfaces.IUserService;
import com.bobko.album.util.AlbumUtils;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IGenericDao<Users, String> userDao;
    
    @Override
    public void addUser(Users user) throws Exception {

        // set default user role
        user.setRole(IUserService.ROLE_ADMIN);
        user.setActive(true);
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();        
        user.setPw(encoder.encodePassword(user.getPw(), null));

        Users checkUser = getUserByName(user.getLogin());
        // check if new user already exists in base
        if ((checkUser != null) && (checkUser.getLogin().equalsIgnoreCase(user.getLogin()))) {
            throw new Exception("Registration failure");
        }        
        
        userDao.add(user);
    }

    public void removeUser(String name) {
        Users entity = userDao.find(name);
        userDao.remove(entity);
    }

    public Users getUser(String name) {
        return userDao.find(name);
    }

    @Override
    public Users getUserByName(String name) {
        List<Users> result = userDao.getByField("login", name);
        return (result != null && !result.isEmpty()) ? result.get(0) : null;
    }

}
