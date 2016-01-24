package com.bobko.album.web;

import java.net.URL;
import java.util.Enumeration;

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
    @RequestMapping(value = "/data/**", method = RequestMethod.GET)
    private byte[] getFile(HttpServletRequest request) {
        return picService.getPicByPath((String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));
    }

}
