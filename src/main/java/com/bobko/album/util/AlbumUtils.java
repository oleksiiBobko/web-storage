package com.bobko.album.util;

/**
 * Generate unique string with <tt>UUID</tt>
 * @author oleksii bobko
 * @data 12.08.2013
 * @see UUID
 */

import java.util.UUID;

public class AlbumUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
