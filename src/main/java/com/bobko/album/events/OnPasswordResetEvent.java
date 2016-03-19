package com.bobko.album.events;

import org.springframework.context.ApplicationEvent;

import com.bobko.album.domain.UserEntity;

public class OnPasswordResetEvent extends ApplicationEvent {

    /**
     * 
     */
    private static final long serialVersionUID = -2205810103730939310L;

    private UserEntity user;
    private String url;
    
    public OnPasswordResetEvent(UserEntity user,
            String url) {
        super(user);
        this.user = user;
        this.url = url;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getConfirmUrl() {
        String token = user.getToken().getToken();
        return url + "/" + token;
    }

}
