package com.bobko.album.dao.interfaces;

/**
 * Interface that provides manipulation with <tt>UserEntity</tt>   
 * 
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserEntity
 */

import com.bobko.album.domain.UserEntity;

public interface IUserDao {
    
    /**
     * Add new user to DB
     * */
    public void addUser(UserEntity user);
    
    /**
     * Remove user from DB by unique name
     * */
    public void removeUser(String name);
    
    /**
     * Retrieve user from DB by unique name
     * */    
    public UserEntity findUser(String name);
    
}
