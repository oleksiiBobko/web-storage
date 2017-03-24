package com.bobko.storage.web;

/**
 * Controller class that provides uploading, grabing, remove pictures and also user login 
 * 
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bobko.storage.common.StorageConst;
import com.bobko.storage.domain.IncomingURL;
import com.bobko.storage.domain.Document;
import com.bobko.storage.service.interfaces.IPagesService;
import com.bobko.storage.service.interfaces.IPictureGrabber;
import com.bobko.storage.service.interfaces.IPictureService;
import com.bobko.storage.util.AlbumUtils;

@Controller
public class UploadController {

    @Autowired
    private IPictureService picService;

    @Autowired
    private IPagesService pagesService;

    @Autowired
    private IPictureGrabber pictureGrubber;

    private static final Logger LOGGER = LogManager.getLogger(UploadController.class);

    private static final String REDIRECT_CONTENT_PAGE = "redirect:/"; 

//    /**
//     * rootPath contains path which will be use to save uploaded pictures
//     * */
//    @Value("${data.root.path}")
//    private String rootPath;

    @RequestMapping("/")
    public String getContent(Map<String, Object> map,
            HttpServletRequest request) {

        List<Document> pictures = picService.list(pagesService.getShift(), StorageConst.PICTURE_COUNT);
        Principal principal = request.getUserPrincipal();
        String userName = null;
        
        if(principal != null) {
            userName = principal.getName();
        }
        
        AlbumUtils.correctPaths(pictures);
        AlbumUtils.setCanDelete(pictures, userName);
        
        map.put("url", new IncomingURL());
        map.put("pages", pagesService.list());
        map.put("pictures", pictures);
//        map.put("authorized", request.isUserInRole(UserRolesTypes.ROLE_USER));
        return "content";
    }

    /**
     * redirect to add picture page method
     * */
    @RequestMapping("/add")
    public String addNew(Map<String, Object> map,
            HttpServletRequest request) {
        map.put("picture", new Document());
        map.put("url", new IncomingURL());
//        map.put("authorized", request.isUserInRole(UserRolesTypes.ROLE_USER));
        return "upload";
    }

    /**
     * method save <tt>Picture</tt> pic metadata into db and binary into file
     * system
     * */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("picture") Document pic, @RequestParam("file") MultipartFile file) {
        try {
            picService.savePicture(pic, file);
        } catch (Exception e) {
            LOGGER.error("Error on save occured", e);
            return "redirect:/add?error=true";
        }

        return REDIRECT_CONTENT_PAGE;
    }

    /**
     * delete picture by unique picture ID
     * */
    @RequestMapping(value = "/delete/{picId}")
    public String deletePicture(@PathVariable("picId") Integer picId) {
        picService.removePicture(picId);
        return REDIRECT_CONTENT_PAGE;
    }

    /**
     * redirect to login page
     * */
    @RequestMapping(value = "/login")
    public String login() {
    	LOGGER.info("try to login");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/";
        }
        return "login";
    }

    /**
     * Method perform picture grabbind from web-page by incomingURL
     * */
    @RequestMapping(value = "/grab")
    public String grub(@ModelAttribute("url") String url,
            @ModelAttribute("option") int option) {

        try {
            pictureGrubber.grub(url, option);
        } catch (Exception e) {
            LOGGER.error("error during grub occured", e);
            return "redirect:/error";
        }
        
        return REDIRECT_CONTENT_PAGE;
    }

    /**
     * skip to next page and redirect to pictures page
     * */
    @RequestMapping(value = "/nextPage")
    public String nextPage() {
        pagesService.nextPage();
        return REDIRECT_CONTENT_PAGE;
    }

    /**
     * skip to previous page and redirect to pictures page
     * */
    @RequestMapping(value = "/prevPage")
    public String prevPage() {
        pagesService.prevPage();
        return REDIRECT_CONTENT_PAGE;
    }

    /**
     * skip to "index" page and redirect to pictures page
     * */
    @RequestMapping(value = "/goto/{index}")
    public String gotoPage(@PathVariable("index") Integer index) {
        pagesService.setShift(index);
        return REDIRECT_CONTENT_PAGE;
    }

}
