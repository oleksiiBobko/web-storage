package com.bobko.album.domain;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;


@Entity
@Table(name = "users")
public class Users {
    
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Size(min = 5, max = 32, message = "The login must be at least 6 characters long.")
    @Column(name = "login")
    private String login;
    
    @Email(regexp="^((?!\\.).*)@(.*)$", message = "Incorrect email format")
    @Column(name = "email")
    private String email;
    
    @Column(name = "pw")
    private String pw;
    
    @Column(name = "role")
    private String role;

    @Column(name = "active")
    private boolean active;
    
    @OneToMany(targetEntity = Picture.class, mappedBy = "user")
    private List<Picture> pictures;

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
    
}
