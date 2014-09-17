package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserService
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.factory.IDaoFactoryMySql;
import com.bobko.album.domain.UserEntity;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	IDaoFactoryMySql daoFactoryMySql;
	
    public void addUser(UserEntity user) {
    	daoFactoryMySql.getUserDao().addUser(user);
    }

    public void removeUser(String name) {
    	daoFactoryMySql.getUserDao().removeUser(name);
    }

    public UserEntity getUser(String name) {
        return daoFactoryMySql.getUserDao().findUser(name);
    }

}
