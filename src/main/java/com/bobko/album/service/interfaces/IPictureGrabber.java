package com.bobko.album.service.interfaces;

import com.bobko.album.domain.IncomingURL;


public interface IPictureGrabber {

    public void grub(IncomingURL url) throws Exception;
    
}
