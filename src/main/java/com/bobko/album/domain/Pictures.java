package com.bobko.album.domain;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name="pictures")
public class Pictures {

    @Column(name="id")
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name="pic_owner")
    private String owner;

    @Column(name="filename")
    private String filename;

    @Column(name="description")
    private String description;
    
    @Column(name="path")
    private String path;
    
    @Column(name="created")
    @Type(type="timestamp")
    private Timestamp created;
    
    @Column(name="thumbnail")
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "userid")
    private Users user;
    
    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

	public String getThumbPath() {
		return path;
	}
	
}
