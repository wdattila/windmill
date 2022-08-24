package com.codecool.windmill.Security;

import com.codecool.windmill.Util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;

        if(token != null && token.startsWith("Bearer ")){
            jwtToken = token.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                System.out.println(username);
            }
            catch (IllegalArgumentException e){
                System.out.println("JWT token does not exist");
            }
            catch (ExpiredJwtException e){
                System.out.println("JWT token has expired");
            }
            catch (MalformedJwtException e){
                System.out.println("JWT token is not correctly formatted or empty");
            }
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);

            if(jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException
    {
        final List<RequestMatcher> ignoredPaths = new ArrayList<>();
        ignoredPaths.add(new AntPathRequestMatcher("/**/login"));
        ignoredPaths.add(new AntPathRequestMatcher("/**/register"));

        return ignoredPaths.stream().anyMatch(antPattern -> antPattern.matches(request));
    }
}
