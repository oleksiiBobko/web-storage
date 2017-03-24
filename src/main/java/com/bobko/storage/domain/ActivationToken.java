package com.bobko.storage.domain;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.bobko.storage.util.AlbumUtils;

@Entity
@Table(name = "activation_token")
public class ActivationToken {
    private static final int EXPIRATION = 60 * 24;
    
    @Id
    @Column(name="token_id", unique=true, nullable=false)
    @GeneratedValue(generator="gen")
    @GenericGenerator(name="gen", strategy="foreign", parameters=@Parameter(name="property", value="user"))
    private int id;
    
    @Column(name="token")
    private String token;
   
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserEntity user;
    
    @Column(name="expire")
    @Type(type="timestamp")
    private Timestamp expire;

    @Column(name="verified")
    private boolean verified;
 
    public ActivationToken() {
        super();
    }
    
    public ActivationToken(UserEntity user) {
        super();
        this.token = AlbumUtils.getUUID();
        this.user = user;
        this.expire = calculateExpiryDate(EXPIRATION);
        this.verified = false;
    }
     
    private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
     
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Timestamp getExpire() {
        return expire;
    }

    public void setExpire(Timestamp expire) {
        this.expire = expire;
    }

    public boolean isExpired() {
        long current = System.currentTimeMillis();
        long expiration = expire.getTime();
        return current > expiration;
    }

    public void reset() {
        this.expire = calculateExpiryDate(EXPIRATION);
        this.verified = false;
        this.token = AlbumUtils.getUUID();
    }

}
