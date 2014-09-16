package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserService
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.UserEntity;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    IUserDao userDAO;
    
    @Transactional
    public void addUser(UserEntity user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void removeUser(String name) {
        userDAO.removeUser(name);
    }

    @Transactional
    public UserEntity getUser(String name) {
        return userDAO.getUser(name);
    }

}
