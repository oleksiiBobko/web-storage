package com.bobko.album.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDAO
 */

import org.springframework.stereotype.Repository;

import com.bobko.album.dao.base.HibernateDao;
import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.Users;

@Repository
public class UserDao extends HibernateDao<Users, String> implements IUserDao<Users, String> {}
