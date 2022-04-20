package com.example.demo.filters;

import com.example.demo.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.RequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class TokenFilter extends OncePerRequestFilter{
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        String key = request.getHeader("api-key") != null ?
                request.getHeader("api-key") : request.getParameter("key");
        if(key == null)
            throw new ServletException("No API key was set");
        if(!authService.validateKey(key))
            throw new ServletException("Invalid API key");
        filterChain.doFilter(request, response);
    }
}
