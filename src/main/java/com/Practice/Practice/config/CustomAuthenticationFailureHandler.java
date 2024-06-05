package com.Practice.Practice.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BadCredentialsException) {
            request.getSession().setAttribute("wrongPasswordError", true);
        } else if (exception instanceof UsernameNotFoundException) {
            // Користувач не знайдений
            request.getSession().setAttribute("noUserError", true);
        }

        // Redirect to the login page
        response.sendRedirect(request.getContextPath() + "/login?error");
    }

}
