package com.bobko.album.service.interfaces;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bobko.album.domain.Pictures;

public interface IPictureService {

    public void addPicture(Pictures pic);

    public List<Pictures> list(int shift, int count);

    public Pictures getPicture(Integer id);
    
    public void removePicture(Integer id);

    public void savePicture(Pictures pic, MultipartFile file) throws Exception;
    
    public byte[] getPicByPath(String path);
    
    public byte[] getThumbPicByPath(String path);

}
