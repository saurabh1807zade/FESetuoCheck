package com.hackathon.javatemplate.auth.config;

import com.hackathon.javatemplate.auth.service.impl.UserDetailServiceImpl;
import com.hackathon.javatemplate.exception.CustomUnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.hackathon.javatemplate.auth.constants.ConfigConstants.*;
import static com.hackathon.javatemplate.auth.constants.ResponseErrors.EXPIRED_ACCESS_TOKEN;
import static com.hackathon.javatemplate.auth.constants.ResponseErrors.INVALID_ACCESS_TOKEN;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String HEADER_STRING = AUTHORIZATION;

    public static final String TOKEN_PREFIX = BEARER;

    @Autowired
    private TokenProvider jwtTokenUtil;


    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        String header = req.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            authToken = header.replace(TOKEN_PREFIX, EMPTY_STRING);
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (ExpiredJwtException e) {
                throw new CustomUnauthorizedException(EXPIRED_ACCESS_TOKEN);
            } catch (Exception e) {
                throw new CustomUnauthorizedException(INVALID_ACCESS_TOKEN);
            }
        } else {
            logger.warn("Couldn't find bearer string, header will be ignored");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthenticationToken(authToken, SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("Authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }


}