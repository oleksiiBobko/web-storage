package com.bobko.album.service.interfaces;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see AlbumPage
 */

import java.util.List;

import com.bobko.album.domain.AlbumPage;

public interface IPagesService {
    
    /**
     * @return List of <tt>AlbumPage</tt>
     * */
    public List<AlbumPage> list();
    
    /**
     * @return number of current page
     * */
    public int getShift();
    
    /**
     * set current page
     * */
    public void setShift(int index);
    
    /**
     * set active +1 page
     * */
    public void nextPage();
    
    /**
     * set active -1
     * */
    public void prevPage();    
    
}
