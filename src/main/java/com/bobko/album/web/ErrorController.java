package com.bobko.album.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {

    @RequestMapping("error")
    public String hendleError() {
        return "error";
    }
    
}
