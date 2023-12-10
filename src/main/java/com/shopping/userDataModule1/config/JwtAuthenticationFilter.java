package com.shopping.userDataModule1.config;

import com.shopping.userDataModule1.exception.InvalidTokenException;
import com.shopping.userDataModule1.exception.TokenExpiredException;
import com.shopping.userDataModule1.service.UserDetailService;
import com.shopping.userDataModule1.service.UserDetailServiceImpl;
import com.shopping.userDataModule1.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException,InvalidTokenException {
        String requestTokenHeader = request.getHeader("Authorization");
        String username=null;
        String jwtToken=null;
        System.out.print(requestTokenHeader);
        if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken=requestTokenHeader.substring(7);
            try {
                username=this.jwtUtil.getUsernameFromToken(jwtToken);
                System.out.println(username);
            }catch(Exception e) {
                
            }
            UserDetails userDetails = this.userDetailServiceImpl.loadUserByUsername(username);
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            Boolean expired = false;
            try{
                expired=this.jwtUtil.isTokenExpired(jwtToken);
            }catch (Exception e){

            }
            if(expired==true){
                throw new TokenExpiredException("expired");
            }
            if(username==null){
                throw new InvalidTokenException("invalid token");
            }
        }
        filterChain.doFilter(request, response);
    }
}
