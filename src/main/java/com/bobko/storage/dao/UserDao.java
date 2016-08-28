package com.bobko.storage.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDAO
 */

import org.springframework.stereotype.Repository;

import com.bobko.storage.dao.base.HibernateDao;
import com.bobko.storage.dao.interfaces.IUserDao;
import com.bobko.storage.domain.UserEntity;

@Repository
public class UserDao extends HibernateDao<UserEntity, String> implements IUserDao {}
