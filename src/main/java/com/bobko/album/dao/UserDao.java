package com.bobko.album.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDAO
 */

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.UserEntity;


@Repository
public class UserDao implements IUserDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void addUser(UserEntity user) throws ConstraintViolationException {
        Session session = sessionFactory.getCurrentSession(); 
        session.save(user);
        session.flush();
    }

    @Override
    public void removeUser(String name) {
        UserEntity user = (UserEntity) sessionFactory.getCurrentSession().load(
                UserEntity.class, name);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public UserEntity findUser(String name) {
        UserEntity user = null;
        try {
        user = (UserEntity) sessionFactory.getCurrentSession().load(
                UserEntity.class, name);
        
        user.getUsrName();
        
        } catch (ObjectNotFoundException ex) {
            return null;
        }
        return user;
    }

}
