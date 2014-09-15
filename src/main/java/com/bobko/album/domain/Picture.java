package com.bobko.album.domain;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "PICTURES")
public class Picture {

    @Column(name = "ID")
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(name = "PIC_OWNER")
    private String owner;

    @Column(name="FILENAME")
    private String filename;

    @Column(name="DESCRIPTION")
    private String description;
    
    @Column(name="PATH")
    private String path;
    
    @Column(name="CREATED")
    @Type(type="timestamp")
    private Timestamp created;
    
    public static final int MAX_DESCRIPTION_SIZE = 100;
    
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
