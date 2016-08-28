package com.bobko.storage.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bobko.storage.domain.Picture;
import com.bobko.storage.domain.Topic;

@Controller
public class MainController {
    /**
     * default request mapping redirect requests to pictures page
     * */
//    @RequestMapping("/")
    public String home(Map<String, Object> model, HttpServletRequest request) {
        
        Picture p = new Picture();
        p.setPath("data/admin/images/fc03856b60bd42769485784afb56317a.jpeg");
        
        Topic stub = new Topic();
        stub.setFrontImage(p);
        stub.setRate(5);
        stub.setFrontImage(p);
        
        List<Topic> topics = new ArrayList<Topic>();
        
        topics.add(stub);
        topics.add(stub);
        topics.add(stub);
        
        model.put("topics", topics);
        return "main";
    }
    
    @RequestMapping(value = "/reset_password")
    public String forgot() {
        return "reset_password";
    }
    
}
