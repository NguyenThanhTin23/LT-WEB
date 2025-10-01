package com.example.jwt.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService uds;
    public JwtAuthFilter(JwtService jwtService,UserDetailsService uds){this.jwtService=jwtService;this.uds=uds;}
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");
        String token=null;
        String username=null;
        if(header!=null && header.startsWith("Bearer ")){
            token=header.substring(7);
            try{username=jwtService.extractUsername(token);}catch(Exception ignored){}
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails user=uds.loadUserByUsername(username);
            if(jwtService.isValid(token,user)){
                UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        chain.doFilter(request,response);
    }
}
