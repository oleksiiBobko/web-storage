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
import com.bobko.album.service.interfaces.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserDao<UserEntity, String> userDao;

    public void addUser(UserEntity user) {
        userDao.add(user);
    }

    public void removeUser(String name) {
        UserEntity entity = userDao.find(name);
        userDao.remove(entity);
    }

    public UserEntity getUser(String name) {
        return userDao.find(name);
    }

}
