package com.bobko.album.service.interfaces;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bobko.album.domain.Picture;

public interface IPictureService {

    public void addPicture(Picture pic);

    public List<Picture> list(int shift, int count);

    public Picture getPicture(Integer id);
    
    public void removePicture(Integer id);

    public void savePicture(Picture pic, MultipartFile file) throws Exception;

}
