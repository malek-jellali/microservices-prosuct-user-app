package com.jellali.serviceutilisateur.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RibbonPortFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Filter initialization logic (if needed)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Proceed with the next filter in the chain
        chain.doFilter(request, response);

        // Get the Ribbon-specific header (if available)
        String ribbonServerPort = httpResponse.getHeader("X-Ribbon-Server-Port");

        if (ribbonServerPort != null) {
            // Log the Ribbon server port
            System.out.println("Request was served by Ribbon port: " + ribbonServerPort);
        } else {
            // Log if the Ribbon header is not found
            System.out.println("No Ribbon server port header found.");
        }
    }

    @Override
    public void destroy() {
        // Cleanup (if needed)
    }
}
