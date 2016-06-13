package com.bobko.album.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import·org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bobko.album.service.interfaces.IPictureGrabber;
import com.bobko.album.service.interfaces.IPictureService;
import com.bobko.album.util.AlbumUtils;

@Service
public class PictureGrabber implements IPictureGrabber {

    private static final int SAVE_FILE = 1;
    private static final int SAVE_LINK = 2;
    private static final int EXTRACT_IMAGES = 3;
    
    @Autowired
    private IPictureService picService;

    /**
     * rootPath contains path which will be use to save uploaded pictures
     * */
    @Value("${data.root.path}")
    String rootPath;

    private static final Logger logger = LogiManager.getLogger(PictureGrabber.class);
    
    
    private static final String PATTERN_STRING = "(https?|ftp|file)://[A-Za-z0-9%./]+(\\.jpg|\\.jpeg|\\.png|\\.gif|\\.tiff|\\.ico)";    

    private List<String> urls;

    @Override
    public void grub(String url, int option) throws Exception {
        String userName = getLoginedUserName();

        File dir = new File(rootPath + "/" + userName + "/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        switch (option) {
        case SAVE_FILE:
            picService.savePicture(url);
            break;
        case SAVE_LINK:
            
            break;
        case EXTRACT_IMAGES:
            urls = createURLList(url);
            for (String s : urls) {
                picService.savePicture(s);
            }
            break;
        default:
            break;
        }

    }

    /**
     * @return authenticated user name
     * */
    private String getLoginedUserName() {
        String userName = "anonimouse";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            userName = authentication.getName();
        }
        return userName;
    } 

    private List<String> createURLList(String url) {
        InputStreamReader in = null;
        List<String> result = new LinkedList<String>();

        if (url == null || url.isEmpty()) {
            return result;
        } else {
            
            try {
                URL url1 = new URL(url);
                // if it's need to
                // Proxy proxy = new Proxy(Proxy.Type.HTTP, new
                // InetSocketAddress("172.30.0.2", 3128));
                URLConnection connection = url1.openConnection();
                connection.addRequestProperty("User-Agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                InputStream inputStream = connection.getInputStream();
                in = new InputStreamReader(inputStream);

                // read contents into string buffer
                StringBuffer input = new StringBuffer();
                
                int ch;
                while ((ch = in.read()) != -1) {
                    input.append((char) ch);
                }

                Pattern pattern = Pattern.compile(PATTERN_STRING, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(input);

                while (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    String match = input.substring(start, end);
                    String[] tmp = match.split("src=");
                    if (tmp.length >= 2) {
                        match = tmp[1].substring(1);
                    }

                    if (match.startsWith("//")) {
                        match = match.substring(1);
                    }

                    if (match.startsWith("/")) {
                        match = AlbumUtils.getPureAdress(url) + match;
                    }

                    result.add(match);
                }
            } catch (Exception e) {
                logger.error(e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        logger.error(e);
                    }
                }
            }
        }

        return result;

    }
    
}
