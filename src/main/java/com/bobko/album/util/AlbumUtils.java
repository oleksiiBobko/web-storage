package com.bobko.album.util;

/**
 * Generate unique string with <tt>UUID</tt>
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UUID
 */

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

public class AlbumUtils {
    
    private static final int MAX_PICTURE_SIZE = 400;
    
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    /**
     * Method set proportional size of picture if picture height or width more
     * then MAX_PICTURE_SIZE
     * */
    public static BufferedImage correctingSize(BufferedImage img) {
        if (img == null)
            return null;
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
            return result.startsWith("www.") ? result.substring(4) : result;
        } catch (URISyntaxException ex) {
            return "";
        }
    }    
    
}
