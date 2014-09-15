package com.bobko.album.service;

/**
 * Service provide access to domain lavel to retrieve information about users from DB
 * @author oleksii bobko
 * @data 12.08.2013
 */

import com.bobko.album.domain.UserEntity;

public interface UserService {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    /**
     * add new user to db
     * */
    public void addUser(UserEntity user);
    
    /**
     * remove user from db by unique user name
     * */
    public void removeUser(String name);
    
    /**
     * retrieve user from db bt unique user name
     * */
    public UserEntity getUser(String name);    
    
}
