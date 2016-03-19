package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserService
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.interfaces.IActivationTokenDao;
import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.ActivationToken;
import com.bobko.album.domain.UserEntity;
import com.bobko.album.exceptions.TokenExpiredException;
import com.bobko.album.exceptions.TokenVerifyedException;
import com.bobko.album.exceptions.UserActivationException;
import com.bobko.album.exceptions.UserNotFoundException;
import com.bobko.album.service.interfaces.IUserService;

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
        
        user.setPwConfirmation(null);
        
        ActivationToken token = new ActivationToken(user);
        user.setToken(token);
        
        userDao.add(user);
        tokenDao.add(token);
    }

    public void removeUser(String name) {
        UserEntity entity = userDao.find(name);
        userDao.remove(entity);
    }

    public UserEntity getUser(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return userDao.find(name);
    }

    @Override
    public UserEntity getUserByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        List<UserEntity> result = userDao.getByField("login", name);
        return (result != null && !result.isEmpty()) ? result.get(0) : null;
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        List<UserEntity> result = userDao.getByField("email", email);
        return (result != null && !result.isEmpty()) ? result.get(0) : null;
    }

    @Override
    public void resetUser(UserEntity user) {
        user.setActive(false);
        user.getToken().reset();
        userDao.update(user);
    }

    @Override
    public UserEntity getUserByToken(String token, boolean activate)
            throws TokenExpiredException, TokenVerifyedException,
            UserNotFoundException {
        List<ActivationToken> result = tokenDao.getByField("token", token);
        UserEntity user = null;
        if (result != null && !result.isEmpty()) {
            ActivationToken tokenEntity = result.get(0);
            if (tokenEntity.isVerified()) {
                throw new TokenVerifyedException();
            }

            if (tokenEntity.isExpired()) {
                throw new TokenExpiredException();
            }
            tokenEntity.setVerified(true);
            user = tokenEntity.getUser();
            user.setActive(activate);
            return user;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void changeUserPassword(UserEntity user, String pw) throws UserActivationException {
        if(user.isActive()) {
            throw new UserActivationException();
        }
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        user.setPw(encoder.encodePassword(pw, null));
        user.setActive(true);
        userDao.update(user);
    }
    
}
