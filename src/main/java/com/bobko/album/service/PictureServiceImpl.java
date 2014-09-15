package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureService
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobko.album.dao.PictureDAO;
import com.bobko.album.domain.Picture;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDAO picDAO;

    @Transactional
    public List<Picture> list(int shift, int count) {
        return picDAO.list(shift, count);
    }

    @Transactional
    public Picture getPicture(Integer id) {
        return picDAO.getPicture(id);
    }
    
    @Transactional
    public void addPicture(Picture pic) {
        picDAO.addPicture(pic);
    }

    @Transactional
    public void removePicture(Integer id) {
        picDAO.removePicture(id);
    }

}
