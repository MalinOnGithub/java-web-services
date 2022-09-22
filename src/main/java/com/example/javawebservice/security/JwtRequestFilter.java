package com.example.javawebservice.security;

import com.example.javawebservice.service.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;
    private UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Vi hämtar värdet från Authorization från Headern som vi får in i requesten
        String authHeader = request.getHeader("Authorization");
        /*Om värdet inte är null så plockar vi ut token där vi tar bort "Bearer". Vi jämför nycklarna så dom matchar.
        * Bodyn som vi fick från token hämtar vi ut ett username från. Baserat på username så hämtar vi ut en User från
        * repot. Ger Usern/Användaren en token att man är autentiserad.*/
        if (authHeader != null){
            String token = authHeader.substring(7);
            Claims body = jwtUtils.parseBody(token);
            String username = body.getSubject();

            UserDetails user = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //Sätter personen som är inloggad
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        //Fortsätter filterflödet
        filterChain.doFilter(request, response);
    }
}
