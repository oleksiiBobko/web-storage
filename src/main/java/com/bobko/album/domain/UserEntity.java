package com.bobko.album.domain;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    
    @Size(min = 5, max = 32, message = "The login must be at least 5 characters long.")
    @Column(name = "login")
    private String login;
    
    @Size(max = 32, message = "First name is too long.")
    @Column(name = "first_name")
    private String firstName;
    
    @Size(max = 32, message = "Last name is too long.")
    @Column(name = "last_name")
    private String lastName;
    
    @Email(regexp="^((?!\\.).*)@(.*)$", message = "Incorrect email format")
    @Column(name = "email")
    private String email;
    
    @Column(name = "pass")
    private String pw;
    
    private String pwConfirmation;
    
    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private boolean active;
    
    @OneToMany(targetEntity = Picture.class, mappedBy = "user")
    private List<Picture> pictures;

    @OneToOne(mappedBy="user", cascade=CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id")
    private ActivationToken token;
    
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }    
    
    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }    
    
    public void setRole(String role) {
        this.role = role;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPwConfirmation() {
        return pwConfirmation;
    }
    
    public ActivationToken getToken() {
        return token;
    }

    public void setToken(ActivationToken token) {
        this.token = token;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPwConfirmation(String pwConfirmation) {
        this.pwConfirmation = pwConfirmation;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
    
    public List<GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (String userRole : getRole().split(",")) {
            setAuths.add(new SimpleGrantedAuthority(userRole));
        }

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }

    public List<GrantedAuthority> getAuthorities(String userRole) {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(new SimpleGrantedAuthority(userRole));
        return result;

    }
    
}
