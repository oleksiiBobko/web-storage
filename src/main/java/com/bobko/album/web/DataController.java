package com.bobko.album.web;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

@Controller
public class DataController {

    /**
     * rootPath contains path which will be use to save uploaded pictures
     * */
    @Value("${data.root.path}")
    String rootPath;
    
    private static final Logger logger = Logger.getLogger(DataController.class);
    
    @ResponseBody
    @RequestMapping(value = "/images/{owner}/**", method = RequestMethod.GET)
    byte[] getFile(@PathVariable String owner, HttpServletRequest request) {
        
        String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
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
    
}
