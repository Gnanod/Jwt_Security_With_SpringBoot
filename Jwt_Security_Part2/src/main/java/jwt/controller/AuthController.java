package jwt.controller;

import jwt.Entity.Permission;
import jwt.Entity.User;
import jwt.dto.PermissionDto;
import jwt.models.AuthenticationRequest;
import jwt.models.AuthenticationResponse;
import jwt.service.MyUserDetailsService;
import jwt.service.PermissionService;
import jwt.service.UserService;
import jwt.util.AuthUtil;
import jwt.util.CommonConstants;
import jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = CommonConstants.ROUTE_AUTH_MAIN)
public class AuthController  {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    private ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)throws Exception{

        User user = userService.getUserByEmail(authenticationRequest.getUsername());

        if (user == null)
            return new ResponseEntity<>(CommonConstants.MESSAGE_ERROR_USER_NULL, HttpStatus.NOT_FOUND);
        List<PermissionDto>  list = permissionService.getPermissionByUserId(Integer.toString(user.getUserId()));

        try{
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
        return  ResponseEntity.ok(generateJwtToken(authenticationRequest.getUsername(), user,list));


    }

    private void authenticate(String username, String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public AuthenticationResponse generateJwtToken(String username, User user, List<PermissionDto> list) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = jwtUtil.generateToken(userDetails, user,list);
        //Create the jwt-response instance
        AuthenticationResponse jwtResponse = new AuthenticationResponse(token);
//        jwtResponse.setName(user.getName());
//        jwtResponse.setEmail(user.getEmail());
//        jwtResponse.setRole(user.getRole());
//        jwtResponse.setPermissionDtos(list);

        return jwtResponse;
    }


}
