package org.helmo.HolyD.secure.jwt;

import org.helmo.HolyD.secure.userDetails.UserDTODetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    private final UserDTODetailsService userDTODetailsService;
    private final JwtService jwtService;

    public JwtFilter(UserDTODetailsService userDTODetailsService, JwtService jwtService) {
        this.userDTODetailsService = userDTODetailsService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = null;
        boolean isTokenExpired = true;
        String headAuth = request.getHeader("Authorization");
        if(headAuth != null && headAuth.startsWith("Bearer ")){
            String token = headAuth.substring(7);
            isTokenExpired = jwtService.isTokenExpired(token);
            username = jwtService.extractUsername(token);
        }
        if(!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDTODetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
