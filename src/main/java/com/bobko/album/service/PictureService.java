package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureService
 */

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bobko.album.dao.interfaces.IPictureDao;
import com.bobko.album.domain.Pictures;
import com.bobko.album.service.interfaces.IPictureService;
import com.bobko.album.util.AlbumUtils;

@Service
@Transactional
public class PictureService implements IPictureService {

    @Autowired
    IPictureDao<Pictures, Integer> pictureDao;

    @Value("${data.root.path}")
    String rootPath;

    private static final String JPG = "jpg";

    private static final int SUFFIX_LENGTH = 3;
    
    private static final Logger logger = Logger.getLogger(PictureService.class);
    
    public List<Pictures> list(int shift, int count) {
        return pictureDao.rankList(shift, count);
    }

    public Pictures getPicture(Integer id) {
        return pictureDao.find(id);
    }

    public void addPicture(Pictures pic) {
        pictureDao.add(pic);
    }

    public void removePicture(Integer id) {
        Pictures entity = pictureDao.find(id);
        pictureDao.remove(entity);
    }

    public void savePicture(Pictures pic, MultipartFile file) throws Exception {
        
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
        if (pic.getDescription().length() >= Pictures.MAX_DESCRIPTION_SIZE) {
            pic.setDescription(pic.getDescription().substring(0,
                    Pictures.MAX_DESCRIPTION_SIZE));
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

    @Override
    public byte[] getPicByPath(String path) {
        
        byte[] fileData = new byte[0];
        
        Path p = FileSystems.getDefault().getPath(rootPath, path);
        try {
            fileData = Files.readAllBytes(p);
        } catch (IOException e) {
            logger.error(e);
        }

        return fileData;
        
    }

    @Override
    public byte[] getThumbPicByPath(String path) {

        byte[] fileData = new byte[0];

        Path p = FileSystems.getDefault().getPath(rootPath, path);

        String suffix = path.substring(path.length() - SUFFIX_LENGTH);
        InputStream stream = null;
        try {
            
            fileData = Files.readAllBytes(p);
            
            if (suffix.equalsIgnoreCase(JPG)) {

                BufferedImage image = ImageIO.read(new ByteArrayInputStream(
                        fileData));

                if (image != null) {

                    image = AlbumUtils.correctingSize(image);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, suffix, baos);
                    fileData = baos.toByteArray();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
        return fileData;
        
    }


    
}
