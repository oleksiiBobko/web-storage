package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see org.springframework.security.core.userdetails.User
 */

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.domain.UserEntity;

@Service("assembler")
public class Assembler {

  @Transactional(readOnly = true)
  User buildUserFromUserEntity(UserEntity userEntity) {

    if(userEntity.getUsrName() == null) {
        return null;
    }
    String username = userEntity.getUsrName();
    String password = userEntity.getPw();
    boolean enabled = userEntity.isActive();
    boolean accountNonExpired = userEntity.isActive();
    boolean credentialsNonExpired = userEntity.isActive();
    boolean accountNonLocked = userEntity.isActive();

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    //for (SecurityRoleEntity role : userEntity.getRoles()) {
      authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
    //}

    User user = new User(username, password, enabled,
      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    return user;
  }
}