package com.bobko.album.domain;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */


import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    private String usrName;
    
    @Column(name = "PASS")
    private String pw;    
    
    @Column(name = "ROLE")
    private String role;

    @Column(name = "ACTIVE")
    private boolean isActive;
    
    @OneToMany(targetEntity = Pictures.class)
    @JoinColumn(name = "id")
    private List<Pictures> pictures;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Pictures> getPictures() {
        return pictures;
    }
    
}
