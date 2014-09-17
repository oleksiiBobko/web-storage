package com.bobko.album.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDAO
 */

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.bobko.album.dao.base.HibernateDao;
import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.UserEntity;


@Repository
public class UserDao extends HibernateDao<UserEntity, String> implements IUserDao {

    @Override
    public void addUser(UserEntity user) throws ConstraintViolationException {
    	super.add(user);
    }

    @Override
    public void removeUser(String name) {
        UserEntity user = super.find(name);
        if (null != user) {
            super.remove(user);
        }
    }

    @Override
    public UserEntity findUser(String name) {
        UserEntity user = null;
        user = super.find(name);
        return user;
    }
}
