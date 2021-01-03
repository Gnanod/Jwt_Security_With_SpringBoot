package jwt.config;

import io.jsonwebtoken.ExpiredJwtException;
import jwt.service.MyUserDetailsService;
import jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest,
//                                    HttpServletResponse httpServletResponse,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String jwtToken = extractJwtFromRequest(httpServletRequest);
//        final String authorizationHeader = httpServletRequest.getHeader("Authorization");
//        String username = null;
//        String jwt = null;
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
//            jwt = authorizationHeader.substring(7);
//            try {
//                username = jwtUtil.getUsernameFromToken(jwtToken);
//            } catch (IllegalArgumentException e) {
//                System.out.println("Unable to get JWT Token");
//            } catch (ExpiredJwtException e) {
//                System.out.println("JWT Token has expired");
//            }
//        }else{
//            logger.warn("JWT Token does not begin with Bearer String");
//        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
//
//            if(jwtUtil.validateToken(jwt,userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails,null,userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//
////        if (StringUtils.hasText(jwtToken) && jwtUtil.validateToken(jwtToken)) {
////            UserDetails userDetails = new User(jwtUtil.getUsernameFromToken(jwtToken), "",
////                    jwtUtil.getRolesFromToken(jwtToken));
////            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
////                    userDetails, null, userDetails.getAuthorities());
////            // After setting the Authentication in the context, we specify
////            // that the current user is authenticated. So it passes the
////            // Spring Security Configurations successfully.
////            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
////        } else {
////            System.out.println("Cannot set the Security Context");
////        }
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        try {
        // JWT Token is in the form "Bearer token". Remove Bearer word and
        // get  only the Token
        String jwtToken = extractJwtFromRequest(request);

        if (StringUtils.hasText(jwtToken) && jwtUtil.validateToken(jwtToken)) {
            String username = jwtUtil.getUsernameFromToken(jwtToken);
            UserDetails userDetail = this.myUserDetailsService.loadUserByUsername(username);
            UserDetails userDetails = new User(jwtUtil.getUsernameFromToken(jwtToken), "",
                    jwtUtil.getRolesFromToken(jwtToken));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            // After setting the Authentication in the context, we specify
            // that the current user is authenticated. So it passes the
            // Spring Security Configurations successfully.
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else {
            System.out.println("Cannot set the Security Context");
        }
        } catch (ExpiredJwtException ex) {
            request.setAttribute("exception", ex);
            throw ex;
        } catch (BadCredentialsException ex) {
            request.setAttribute("exception", ex);
            throw ex;
        }
        chain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
