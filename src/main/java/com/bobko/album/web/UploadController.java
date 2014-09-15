package com.bobko.album.web;

/**
 * Controller class that provides uploading, grabing, remove pictures and also user login 
 * 
 * @author oleksii bobko
 * @data 12.08.2013
 */

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.bobko.album.domain.Picture;
import com.bobko.album.domain.IncomingURL;
import com.bobko.album.service.PagesService;
import com.bobko.album.service.PictureService;
import com.bobko.album.util.AlbumUtils;
import com.bobko.album.util.PictureGrabber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    public static final int MAX_PICTURE_SIZE = 400;
    
    @Autowired
    private PictureService picService;
    
    @Autowired
    private PagesService pagesService;    
    
    /**
     * */
    @Autowired
    private ServletContext context;

    /**
     * rootPath contains path which will be use to save uploaded pictures 
     * */
    @Value("${data.root.path}")
    String rootPath;
    
    /**
     * */
    @RequestMapping("/pictures")
    public String listPictures(Map<String, Object> map) {
        map.put("url", new IncomingURL());
        map.put("pages", pagesService.list());
        map.put("pictures", picService.list(pagesService.getShift(), PagesService.PICTURE_COUNT));
        return "picturesList";
    }
    
    /**
     * default request mapping redirect requests to pictures page 
     * */
    @RequestMapping("/")
    public String home() {
        return "redirect:/pictures";
    }

    /**
     * redirect to add picture page method
     * */
    @RequestMapping("/add")
    public String addNew(Map<String, Object> map) {
        map.put("picture", new Picture());
        return "addPicture";
    }

    /**
     * method save <tt>Picture</tt> pic metadata into db and binary into file system
     * */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("picture") Picture pic,
            @RequestParam("file") MultipartFile file) {

        pic.setFilename(file.getOriginalFilename());

        // check if field is correct
        if ((pic.getOwner() == null) || (pic.getOwner().isEmpty())
                || (pic.getDescription() == null)
                || (pic.getDescription().isEmpty())
                || (pic.getFilename() == null) || (pic.getFilename().isEmpty())) {
            return "redirect:/pictures?error=true";
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
        final String uuid = AlbumUtils.getUUID();
        File multipartFile = new File(dir + "/" + uuid + "." + suffix);
        try {
            file.transferTo(multipartFile);
            
            pic.setPath(username + "/" + uuid + "." + suffix);
            
            picService.addPicture(pic);            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/pictures?error=true";

        }
        
        
        return "redirect:/pictures";
    }

    /**
     * @return byte[] that contains binary data of picture. Picture retrieve from db by unique id 
     * */
    @RequestMapping(value = "/image/{imageId}")
    @ResponseBody
    public byte[] getImage(@PathVariable int imageId) {
        String path = null;
        byte[] data = null;
        BufferedImage image = null;
            path = picService.getPicture(imageId).getPath();
            InputStream stream = null;
            File file = new File(rootPath + "/" + path);
            try {
                data = new byte[(int)file.length()];
                image = correctingSize(ImageIO.read(file));
                
                if(image != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(image, "png", baos);
                    data = baos.toByteArray();
                }                
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if(stream != null) {
                    try {
                    stream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } 
        return data;
    }

	@RequestMapping(value = "/thumbimage/{imageId}")
	@ResponseBody
	public byte[] getThumbImage(@PathVariable int imageId) {
		String path = null;
		byte[] data = null;
		BufferedImage image = null;
		path = picService.getPicture(imageId).getThumbPath();
		InputStream stream = null;
		File file = new File(rootPath + File.separator + path);
		try {
			data = new byte[(int) file.length()];
			image = ImageIO.read(file);

			if (image != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "png", baos);
				data = baos.toByteArray();
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
		return data;
	}    
    
    /**
     * Method set proportional size of picture if picture height or width more then MAX_PICTURE_SIZE 
     * */
    public BufferedImage correctingSize(BufferedImage img) {
        if(img == null) return null;
        int oldWidth = img.getWidth();
        int oldHeight = img.getHeight();

        if((oldHeight <= MAX_PICTURE_SIZE) && (oldWidth <= MAX_PICTURE_SIZE)) {
            return img;
        }
        double ratio = Math.min((double)MAX_PICTURE_SIZE / (double)oldHeight, (double)MAX_PICTURE_SIZE / (double)oldWidth);
        int newWidth = (int)(oldWidth * ratio);
        int newHeight = (int)(oldHeight * ratio);
        
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, oldWidth, oldHeight, null);
        g.dispose();
        return dimg;
    }
    
    /**
     * delete picture by unique picture ID
     * */
    @RequestMapping(value = "/delete/{picId}")
    public String deletePicture(@PathVariable("picId") Integer picId) {
        picService.removePicture(picId);
        return "redirect:/pictures";
    }

    /**
     * redirect to login page
     * */
    @RequestMapping(value = "/login")
    public String login() {
        return "redirect:login.jsp";
    }
    
    /**
     * Method perform picture grabbind from web-page by incomingURL
     * */
    @RequestMapping(value = "/grab")
    public String grub(@ModelAttribute("URL") IncomingURL uRL) {

        String userName = getLoginedUserName();
        
        File dir = new File(rootPath + "/" + userName + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }        
        
        PictureGrabber graber = new PictureGrabber(uRL.getURL(), rootPath, userName);
        Picture coin = null;
        while((coin = graber.getNextPicture()) != null) {
            picService.addPicture(coin); 
        }
        
        return "redirect:/pictures";
    }
    
    /**
     * @return authenticated user name
     * */
    private String getLoginedUserName() {
        String userName = "anonimouse";
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if(authentication != null) {
            userName = authentication.getName(); 
        }
        return userName;
    }
    
    /**
     * skip to next page and redirect to pictures page
     * */
    @RequestMapping(value = "/nextPage")
    public String nextPage() {
        pagesService.nextPage();
        return "redirect:pictures";
    }
    
    
    /**
     * skip to previous page and redirect to pictures page
     * */
    @RequestMapping(value = "/prevPage")
    public String prevPage() {
        pagesService.prevPage();
        return "redirect:pictures";
    }
    
    /**
     * skip to "index" page and redirect to pictures page
     * */
    @RequestMapping(value = "/goto/{index}")
    public String gotoPage(@PathVariable("index") Integer index) {
        pagesService.setShift(index);
        return "redirect:/pictures";
    }    
    
}
