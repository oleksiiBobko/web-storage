package com.bobko.album.service;

/**
 * @author oleksii bobko
 * @data 12.08.2013
 * @see PictureService
 */

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

import com.bobko.album.common.AlbumConst;
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
    private static final String IMAGES = "images";
    private static final String THUMBNAIL = "thumbnail";
    private static final int SUFFIX_LENGTH = 3;
    private final static int size = 1024;    
    
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

    public void savePicture(Pictures pic, MultipartFile multipartFile) throws Exception {
        
        pic.setFilename(multipartFile.getOriginalFilename());

        // check if field is correct
        //TODO: validate model
        if ((pic.getOwner() == null) || (pic.getOwner().isEmpty())
                || (pic.getDescription() == null)
                || (pic.getDescription().isEmpty())
                || (pic.getFilename() == null) || (pic.getFilename().isEmpty())) {
            throw new Exception();
        }

        String username = getLoginedUserName();

        // normalize description length
        if (pic.getDescription().length() >= AlbumConst.MAX_DESCRIPTION_SIZE) {
            pic.setDescription(pic.getDescription().substring(0, AlbumConst.MAX_DESCRIPTION_SIZE));
        }

        File dir = new File(rootPath + File.separator + username + File.separator + IMAGES + File.separator);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File thumbnailDir = new File(rootPath + File.separator + username + File.separator + THUMBNAIL + File.separator);
        if (!thumbnailDir.exists()) {
            thumbnailDir.mkdirs();
        }        
        
        String fileName = pic.getFilename();

        String suffix = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            suffix = fileName.substring(i + 1);
        }
        String uuid = AlbumUtils.getUUID();
        File image = new File(dir + File.separator + uuid + "." + suffix);
        multipartFile.transferTo(image);
        
        File thumbnail = new File(thumbnailDir + File.separator + uuid + "." + suffix);
        BufferedImage bufferedImage = ImageIO.read(image);
        bufferedImage = AlbumUtils.correctingSize(bufferedImage);
        ImageIO.write(bufferedImage, suffix, thumbnail);
        
        pic.setPath(username + File.separator + uuid + "." + suffix);
        addPicture(pic);
        
    }
    
    private String downloadFile(String url) {
        
        String username = getLoginedUserName();
        
        File dir = new File(rootPath + File.separator + username + File.separator + IMAGES + File.separator);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File thumbnailDir = new File(rootPath + File.separator + username + File.separator + THUMBNAIL + File.separator);
        if (!thumbnailDir.exists()) {
            thumbnailDir.mkdirs();
        }
        
        OutputStream outStream = null;
        HttpURLConnection connection = null;

        InputStream is = null;
        try {
            URL urlToPicture;
            byte[] buf;
            int byteRead, byteWritten = 0;
            urlToPicture = new URL(url);
            
            String uuid = AlbumUtils.getUUID();
            
            String suffix = "";

            int i = url.lastIndexOf('.');
            if (i > 0) {
                suffix = url.substring(i + 1);
            }
            
            File image = new File(dir + File.separator + uuid + "." + suffix);
            
            outStream = new BufferedOutputStream(new FileOutputStream(image));

            // Proxy proxy = new Proxy(Proxy.Type.HTTP, new
            // InetSocketAddress("172.30.0.2", 3128));

            connection = (HttpURLConnection) urlToPicture.openConnection();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println(connection.getErrorStream());
                return "";
            } else {
                is = connection.getInputStream();
                buf = new byte[size];
                while ((byteRead = is.read(buf)) != -1) {
                    outStream.write(buf, 0, byteRead);
                    byteWritten += byteRead;
                }
                
                File thumbnail = new File(thumbnailDir + File.separator + uuid + "." + suffix);
                
                BufferedImage bufferedImage = ImageIO.read(image);
                
                bufferedImage = AlbumUtils.correctingSize(bufferedImage);
                
                ImageIO.write(bufferedImage, suffix, thumbnail);
                
                logger.debug("Downloaded Successfully.");
                logger.debug("File name:\"" + uuid + "." + suffix
                        + "\"\nNo ofbytes :" + byteWritten);
                return image.toString();
            }

        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                if (is != null)
                    is.close();
                if (outStream != null)
                    outStream.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
        return "";
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
    public void createPicture(String url) {
        
        String userName = getLoginedUserName();
        
        Pictures pic = new Pictures();
        String path = downloadFile(url);
        if (path == null || path.isEmpty()) {
            return;
        }
        pic.setPath(path);
        pic.setDescription(AlbumUtils.getPureAdress(url));
        int slashIndex = url.lastIndexOf('/');
        String originalFileName = url.substring(slashIndex + 1);
        if ((originalFileName != null) && !originalFileName.isEmpty()) {
            pic.setFilename(originalFileName);
        } else {
            pic.setFilename("imgUrl");
        }
        pic.setOwner(userName);
        addPicture(pic);
    }
    
}
