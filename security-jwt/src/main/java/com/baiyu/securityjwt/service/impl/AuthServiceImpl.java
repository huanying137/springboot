package com.baiyu.securityjwt.service.impl;

import com.baiyu.securityjwt.service.AuthService;
import com.baiyu.securityjwt.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author baiyu
 * @data 2020-05-19 13:37
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken( username, password );
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }



}
