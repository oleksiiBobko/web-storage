package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PagesService
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.interfaces.IPagesHolderDao;
import com.bobko.album.domain.AlbumPage;
import com.bobko.album.service.interfaces.IPagesService;


@Service
public class PagesService implements IPagesService {

    @Autowired
    IPagesHolderDao pagesDao;
    
    @Transactional
    public List<AlbumPage> list() {
        return pagesDao.list();
    }

    @Transactional
    public int getShift() {
        return pagesDao.getShift();
    }

    @Transactional
    public void setShift(int index) {
        pagesDao.setShift(index);
    }

    @Transactional
    public void nextPage() {
        pagesDao.nextPage();
    }

    @Transactional
    public void prevPage() {
        pagesDao.prevPage();
    }

}
