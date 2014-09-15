package com.bobko.album.domain;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USERS")
public class UserEntity {
    
    @Id
    @Column(name = "LOGIN")
    private String usrName;
    
    @Column(name = "PASS")
    private String pw;    
    
    @Column(name = "ROLE")
    private String role;

    @Column(name = "ACTIVE")
    private boolean isActive;    
        
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

}
