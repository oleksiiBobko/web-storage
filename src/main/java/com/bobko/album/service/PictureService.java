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

import com.bobko.album.dao.interfaces.IPictureDao;
import com.bobko.album.domain.Picture;
import com.bobko.album.service.interfaces.IPictureService;

@Service
@Transactional
public class PictureService implements IPictureService {

    @Autowired
    IPictureDao<Picture, Integer> pictureDao;

    public List<Picture> list(int shift, int count) {
        return pictureDao.rankList(shift, count);
    }

    public Picture getPicture(Integer id) {
        return pictureDao.find(id);
    }

    public void addPicture(Picture pic) {
        pictureDao.add(pic);
    }

    public void removePicture(Integer id) {
        Picture entity = pictureDao.find(id);
        pictureDao.remove(entity);
    }

}
