package com.bobko.album.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.bobko.album.service.interfaces.IPictureService;

@Controller
public class DataController {

    @Autowired
    private IPictureService picService;

    @ResponseBody
    @RequestMapping(value = "/images/**", method = RequestMethod.GET)
    private byte[] getFile(HttpServletRequest request) {
        String path = ((String) request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE))
                .substring("/images".length());
        return picService.getPicByPath(path);
    }

    @RequestMapping(value = "/thumbimage/**")
    @ResponseBody
    private byte[] getThumbImage(HttpServletRequest request) {
        String path = ((String) request
                .getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE))
                .substring("/thumbimage".length());        
        return picService.getThumbPicByPath(path);
    }

}
