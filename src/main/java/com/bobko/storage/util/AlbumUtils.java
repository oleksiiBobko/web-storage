package com.bobko.storage.util;

/**
 * Generate unique string with <tt>UUID</tt>
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UUID
 */

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.bobko.storage.domain.Document;

public class AlbumUtils {
    
    private static final int MAX_PICTURE_SIZE = 400;
    private static final int PREFIX_SIZE = 4;
    
    private static final Logger LOGGER = LogManager.getLogger(AlbumUtils.class);
    
    private AlbumUtils() {
    }
    
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * Method set proportional size of picture if picture height or width more
     * then MAX_PICTURE_SIZE
     * */
    public static BufferedImage correctingSize(BufferedImage img) {
        if (img == null) {
            return null;
        }
        int oldWidth = img.getWidth();
        int oldHeight = img.getHeight();

        if ((oldHeight <= MAX_PICTURE_SIZE) && (oldWidth <= MAX_PICTURE_SIZE)) {
            return img;
        }
        double ratio = Math.min((double) MAX_PICTURE_SIZE / (double) oldHeight,
                (double) MAX_PICTURE_SIZE / (double) oldWidth);
        int newWidth = (int) (oldWidth * ratio);
        int newHeight = (int) (oldHeight * ratio);

        BufferedImage dimg = new BufferedImage(newWidth, newHeight,
                img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newWidth, newHeight, 0, 0, oldWidth, oldHeight,
                null);
        g.dispose();
        return dimg;
    }
    
    public static String getPureAdress(String url) {
        try {
            URI uri = new URI(url);
            String result = uri.getScheme() + "://" + uri.getHost();
            return result.startsWith("www.") ? result.substring(PREFIX_SIZE) : result;
        } catch (URISyntaxException ex) {
            LOGGER.warn("bad URI syntax", ex);
            return "";
        }
    }

    public static void correctPaths(List<Document> pictures) {
        for (Document picture : pictures) {
            picture.setPath(picture.getPath().replace("\\", "/"));
        }
        
    }
    
    public static String getMD5(String input) throws Exception {

        MessageDigest messageDigest = null;
        String encoded = "";
        
        // encode pw to md5 hash
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes(), 0, input.length());
            encoded = new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Encription failure", e);
        }

        return encoded;
        
    }

    public static void setCanDelete(List<Document> pictures, String userName) {
        
        for(Document p : pictures) {
            if(userName != null && !userName.isEmpty() && p.getOwner().equals(userName)) {
                p.setCanDelete(true);
            } else {
                p.setCanDelete(false);
            }
        }
        
    }
    
}
