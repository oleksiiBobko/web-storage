package com.bobko.album.dao.interfaces;

/**
 * Interface that provides manipulation with <tt>Picture</tt>   
 * 
 * @author oleksii bobko
 * @data 12.08.2013
 * @see Picture
 */

import java.util.List;

import com.bobko.album.domain.Picture;

public interface IPictureDao {

    /**
     * Add new picture to DB
     * */
    public void addPicture(Picture pic);

    /**
     * Retrieve <tt>List</tt> of <tt>Pictures</tt> from DB
     * */
    public List<Picture> list(int shift, int count);

    /**
     * Retrieve <tt>Picture</tt> from DB by unique ID
     * */
    public Picture find(int id);
    
    /**
     * Remove <tt>Picture</tt> from DB by unique ID
     * */
    public void removePicture(int id);

}