package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see AlbumPage
 */

import java.util.List;

import com.bobko.album.domain.AlbumPage;

public interface PagesService {
    
    /**
     * value PICTURE_COUNT define number of pictures per one page
     * */
    public static final int PICTURE_COUNT = 5;
    /**
     * value MAX_PAGES_COUNT define max number of pages link on pagination  
     * */
    public static final int MAX_PAGES_COUNT = 6;
    
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
