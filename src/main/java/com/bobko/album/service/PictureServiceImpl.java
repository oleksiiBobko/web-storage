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

import com.bobko.album.dao.factory.IDaoFactoryMySql;
import com.bobko.album.domain.Picture;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

	@Autowired
	IDaoFactoryMySql daoFactoryMySql;
	
    public List<Picture> list(int shift, int count) {
        return daoFactoryMySql.getPictureDao().list(shift, count);
    }

    public Picture getPicture(Integer id) {
        return daoFactoryMySql.getPictureDao().find(id);
    }
    
    public void addPicture(Picture pic) {
    	daoFactoryMySql.getPictureDao().addPicture(pic);
    }

    public void removePicture(Integer id) {
    	daoFactoryMySql.getPictureDao().removePicture(id);
    }

}
