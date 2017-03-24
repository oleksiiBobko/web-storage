package com.bobko.storage.service.interfaces;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bobko.storage.domain.Document;

public interface IPictureService {

    public void addPicture(Document pic);

    public List<Document> list(int shift, int count);

    public Document getPicture(int id);
    
    public void removePicture(int id);

    public void savePicture(Document pic, MultipartFile file) throws Exception;
    
    public byte[] getPicByPath(String path);
    
    public void savePicture(String url);

}
