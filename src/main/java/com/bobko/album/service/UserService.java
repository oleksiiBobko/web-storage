package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserService
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.base.IGenericDao;
import com.bobko.album.domain.Users;
import com.bobko.album.service.interfaces.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IGenericDao<Users, String> userDao;
    
    public void addUser(Users user) {
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
        List<Users> result = userDao.getByField("usrName", name);
        return (result != null && !result.isEmpty()) ? result.get(0) : null;
    }

}
