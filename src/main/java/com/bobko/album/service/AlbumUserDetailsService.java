package com.bobko.album.service;

/**
 * Service that provide user exists by username using almost at registration or authorization
 * 
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UserDetailsService
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.common.UserRolesTypes;
import com.bobko.album.domain.Users;
import com.bobko.album.service.interfaces.IUserService;

@Service("albumUserDetailsService")
public class AlbumUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService userService;
    
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String user)
            throws UsernameNotFoundException, DataAccessException {

        Users userEntity = userService.getUserByName(user);
        if (userEntity == null)         
            throw new UsernameNotFoundException("user not found");

        List<GrantedAuthority> authorities = buildUserAuthority();
        
        return buildUserFromUserEntity(userEntity, authorities);
    }

    private UserDetails buildUserFromUserEntity(Users user, List<GrantedAuthority> authorities) {
        return new User(user.getLogin(), user.getPw(), user.isActive(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority() {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        //for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(UserRolesTypes.ROLE_ADMIN));
        //}

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);

        return result;
    }
    
}