package com.bobko.album.web;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.bobko.album.service.interfaces.IPictureService;
import com.bobko.album.util.AlbumUtils;

@Controller
public class DataController {

    /**
     * rootPath contains path which will be use to save uploaded pictures
     * */
    @Value("${data.root.path}")
    String rootPath;

    @Autowired
    private IPictureService picService;

    private static final String JPG = "jpg";

    private static final int SUFFIX_LENGTH = 3;

    private static final Logger logger = Logger.getLogger(DataController.class);

    @ResponseBody
    @RequestMapping(value = "/images/{owner}/**", method = RequestMethod.GET)
    private byte[] getFile(@PathVariable String owner,
            HttpServletRequest request) {

        String path = (String) request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        byte[] fileData = new byte[0];
        path = path.substring("/images".length());
        Path p = FileSystems.getDefault().getPath(rootPath, path);
        try {
            fileData = Files.readAllBytes(p);
        } catch (IOException e) {
            logger.error(e);
        }

        logger.debug("file = " + path);

        return fileData;
    }

    @RequestMapping(value = "/thumbimage/{owner}/**")
    @ResponseBody
    private byte[] getThumbImage(@PathVariable String owner,
            HttpServletRequest request) {

        byte[] fileData = new byte[0];
        String path = (String) request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        path = path.substring("/thumbimage".length());
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
