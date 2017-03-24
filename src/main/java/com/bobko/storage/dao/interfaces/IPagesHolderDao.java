package com.bobko.storage.dao.interfaces;

/**
 * Interface that provides navigation and pagination 
 * 
 * @author oleksii bobko
 * @data 12.08.2013
 * @see AlbumPagesHolderDAO
 * @see AlbumPage
 */

import java.util.List;

import com.bobko.storage.domain.StoragePage;

public interface IPagesHolderDao {
   
    /**
     * @return <tt>List</tt> of pages that provides handling active page and count of pages
     * */
    public List<StoragePage> list();
    
    /**
     * @return absolute number of current page
     * */
    public int getShift();
    
    /**
     * This method sets currently active page
     * */
    public void setShift(int index);
    
    /**
     * This method provide one move to next page 
     * */    
    public void nextPage();
    
    /**
     * This method provide one move to previous page 
     * */
    public void prevPage();
    
}
