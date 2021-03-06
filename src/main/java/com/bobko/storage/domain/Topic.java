package com.bobko.storage.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Associated with any topic on the site
 * 
 * @author oleksii bobko
 * @data 08.02.2016
 */

//@Entity
//@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    
    @Column(name="title")
    private String title;
    
    @Column(name="front_image")
    private Document frontImage;
    
    @Column(name="tags")
    private String tags;
    
    @Column(name="rate")
    private int rate;
    
    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Document getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(Document frontImage) {
        this.frontImage = frontImage;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

}
