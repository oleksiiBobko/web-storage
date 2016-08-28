/**
 * 
 * */
package com.bobko.storage.component;

/**
 * This class provides logout event handling and redirect page to login page
 * @author oleksii bobko
 * @data 12.08.2013
 * @see SimpleUrlLogoutSuccessHandler
 */

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.
SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 
 * */@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
            final HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        if (authentication != null) {
            authentication.getName();
        }

        setDefaultTargetUrl("/");
        super.onLogoutSuccess(request, response, authentication);
    }
}
