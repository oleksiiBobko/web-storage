package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureService
 */

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bobko.album.dao.interfaces.IPictureDao;
import com.bobko.album.domain.Picture;
import com.bobko.album.service.interfaces.IPictureService;
import com.bobko.album.util.AlbumUtils;

@Service
@Transactional
public class PictureService implements IPictureService {

    @Autowired
    IPictureDao<Picture, Integer> pictureDao;

    @Value("${data.root.path}")
    String rootPath;
    
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

    public void savePicture(Picture pic, MultipartFile file) throws Exception {
        
        pic.setFilename(file.getOriginalFilename());

        // check if field is correct
        if ((pic.getOwner() == null) || (pic.getOwner().isEmpty())
                || (pic.getDescription() == null)
                || (pic.getDescription().isEmpty())
                || (pic.getFilename() == null) || (pic.getFilename().isEmpty())) {
            throw new Exception();
        }

        String username = getLoginedUserName();

        // normalize description length
        if (pic.getDescription().length() >= Picture.MAX_DESCRIPTION_SIZE) {
            pic.setDescription(pic.getDescription().substring(0,
                    Picture.MAX_DESCRIPTION_SIZE));
        }

        File dir = new File(rootPath + "/" + username + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = pic.getFilename();

        String suffix = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            suffix = fileName.substring(i + 1);
        }
        String uuid = AlbumUtils.getUUID();
        File multipartFile = new File(dir + "/" + uuid + "." + suffix);
        file.transferTo(multipartFile);
        pic.setPath(username + "/" + uuid + "." + suffix);
        addPicture(pic);
    }
    
    /**
     * @return authenticated user name
     * */
    private String getLoginedUserName() {
        String userName = "anonimouse";
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            userName = authentication.getName();
        }
        return userName;
    }


    
}
