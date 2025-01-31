package jwt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String message;
        // Check if the request as any exception that we have stored in Request
        final Exception exception = (Exception) httpServletRequest.getAttribute("exception");

        if (exception != null) {
            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("cause", exception.toString()));
            httpServletResponse.getOutputStream().write(body);
        } else {

            if (e.getCause() != null) {
                message = e.getCause().toString() + " " + e.getMessage();
            } else {
                message = e.getMessage();
            }
            byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap("error", message));
            httpServletResponse.getOutputStream().write(body);
        }

    }
}
