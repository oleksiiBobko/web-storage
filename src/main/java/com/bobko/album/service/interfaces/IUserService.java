package com.bobko.album.service.interfaces;

/**
 * Service provide access to domain lavel to retrieve information about users from DB
 * @author oleksii bobko
 * @data 12.08.2013
 */

import com.bobko.album.domain.UserEntity;
import com.bobko.album.exceptions.TokenExpiredException;
import com.bobko.album.exceptions.TokenVerifyedException;
import com.bobko.album.exceptions.UserActivationException;
import com.bobko.album.exceptions.UserNotFoundException;

public interface IUserService {

    public static final String ROLE_USER = "ROLE_USER";
    /**
     * add new user to db
     * */
    public void addUser(UserEntity user) throws Exception;
    
    /**
     * remove user from db by unique user name
     * */
    public void removeUser(String name);
    
    /**
     * retrieve user from db bt unique user name
     * */
    public UserEntity getUser(String name); 
    
    public UserEntity getUserByName(String name);
    
    public UserEntity getUserByEmail(String email);

    public void resetUser(UserEntity user);

    public UserEntity getUserByToken(String token, boolean activate) throws TokenExpiredException, TokenVerifyedException, UserNotFoundException;

    public void changeUserPassword(UserEntity oldUser, String pw) throws UserActivationException;
    
}
