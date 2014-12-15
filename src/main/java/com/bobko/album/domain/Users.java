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


@Entity
@Table(name = "USERS")
public class Users {
    
    @Id
    @GeneratedValue
    @Column(name="ID")
    private Integer id;
    
    @Column(name = "LOGIN")
    private String login;
    
    @Column(name = "PASS")
    private String pw;    
    
    @Column(name = "ROLE")
    private String role;

    @Column(name = "ACTIVE")
    private boolean active;
    
    @OneToMany(targetEntity = Pictures.class, mappedBy = "userId")
    private List<Pictures> pictures;

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

    public List<Pictures> getPictures() {
        return pictures;
    }
    
}
