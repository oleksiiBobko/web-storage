package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserService
 */

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.interfaces.IActivationTokenDao;
import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.ActivationToken;
import com.bobko.album.domain.UserEntity;
import com.bobko.album.service.interfaces.IUserService;
import com.bobko.album.util.AlbumUtils;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;
    
    @Autowired
    private IActivationTokenDao tokenDao;
    
    public UserService() {
        super();
    }
    
    @Override
    public void addUser(UserEntity user) throws Exception {

        // set default user role
        user.setRole(IUserService.ROLE_USER);
        
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPw(encoder.encodePassword(user.getPw(), null));
        
        ActivationToken token = new ActivationToken(AlbumUtils.getUUID(), user);
        user.setToken(token);
        
        userDao.add(user);
        tokenDao.add(token);
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

    @Override
    public void activateUser(String token) {
        List<ActivationToken> result = tokenDao.getByField("token", token);
        
        if(result != null && !result.isEmpty()) {
            ActivationToken tokenEntity = result.get(0);
            tokenEntity.getUser().setActive(true);
        }
        
    }
    
}
