package com.bobko.album.service;

/**
 * Service that provide user exists by username using almost at registration or authorization
 * 
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDetailsService
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.interfaces.IUserDao;
import com.bobko.album.domain.UserEntity;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired 
  private IUserDao dao;
  @Autowired 
  private Assembler assembler;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String user)
      throws UsernameNotFoundException, DataAccessException {

    //UserDetails userDetails = null;
    UserEntity userEntity = dao.getUser(user);
    if (userEntity == null)
      throw new UsernameNotFoundException("user not found");

    return assembler.buildUserFromUserEntity(userEntity);
  }
}