package com.bobko.album.dao;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureDAO
 */

import java.util.List;

import com.bobko.album.dao.base.HibernateDao;
import com.bobko.album.dao.interfaces.IPictureDao;
import com.bobko.album.domain.Picture;

import org.springframework.stereotype.Repository;

@Repository
public class PictureDao extends HibernateDao<Picture, Integer> implements IPictureDao {

    public void addPicture(Picture pic) {
        super.add(pic);
    }
    
    public List<Picture> list(int shift, int count) {
    	return super.rankList(shift, count);
    }

    public Picture find(int id) {
    	return super.find(id);
    }    
    
    public void removePicture(int id) {
        Picture pic = super.find(id);
        if (null != pic) {
            super.remove(pic);
        }
    }

}
