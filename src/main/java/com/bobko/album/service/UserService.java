package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserService
 */

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.interfaces.IActivationTokenDao;
import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.ActivationToken;
import com.bobko.album.domain.UserEntity;
import com.bobko.album.service.interfaces.IMailService;
import com.bobko.album.service.interfaces.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;
    
    @Autowired
    private IActivationTokenDao tokenDao;
    
    @Autowired
    private IMailService mailservice;
    
    public UserService() {
        super();
    }
    
    @Override
    public void addUser(UserEntity user) throws Exception {

        // set default user role
        user.setRole(IUserService.ROLE_ADMIN);
        
        if (user.getPw().equals(user.getPwConfirmation())) {
            Md5PasswordEncoder encoder = new Md5PasswordEncoder();
            user.setPw(encoder.encodePassword(user.getPw(), null));
        } else {
            throw new Exception("password confirmation do not match password="
                    + user.getPw() + " confirmation="
                    + user.getPwConfirmation());
        }

        UserEntity checkUser = getUserByName(user.getLogin());
        // check if new user already exists in base
        if ((checkUser != null) && (checkUser.getLogin().equalsIgnoreCase(user.getLogin()))) {
            throw new Exception("login name already in use");
        }
        
        UserEntity emailUser = getUserByEmail(user.getEmail());
        // check if new user already exists in base
        if ((emailUser != null) && (emailUser.getLogin().equalsIgnoreCase(user.getEmail()))) {
            throw new Exception("email already in use");
        }
        
        ActivationToken token = new ActivationToken(UUID.randomUUID().toString(), user);
        user.setToken(token);
        
        userDao.add(user);
        tokenDao.add(token);
        mailservice.sendMail("oleksii.bobko@gmail.com", user.getEmail(), "noreply", user.getToken().getToken());
    }

    public void removeUser(String name) {
        UserEntity entity = userDao.find(name);
        userDao.remove(entity);
    }

    public UserEntity getUser(String name) {
        return userDao.find(name);
    }

    @Override
    public UserEntity getUserByName(String name) {
        List<UserEntity> result = userDao.getByField("login", name);
        return (result != null && !result.isEmpty()) ? result.get(0) : null;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        List<UserEntity> result = userDao.getByField("email", email);
        return (result != null && !result.isEmpty()) ? result.get(0) : null;
    }
    
}
