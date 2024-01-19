package com.lilcodeur.school.securiter;

import com.lilcodeur.school.services.ElevesServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@AllArgsConstructor
@Service
public class JwtFilter extends OncePerRequestFilter {
    private JwtSerices jwtSerices;
    private ElevesServices elevesServices;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String userName = null;
        boolean isTokenExpired = true;
        String authorization = request.getHeader("Authorization");
        if(authorization!=null && authorization.startsWith("Bearer ")){
            token = authorization.substring(7);
            isTokenExpired = jwtSerices.isTokenExpire(token);
            userName = jwtSerices.getUserNameToken(token);
        }

        if(!isTokenExpired && userName!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails eleves = elevesServices.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(eleves,null,eleves.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
