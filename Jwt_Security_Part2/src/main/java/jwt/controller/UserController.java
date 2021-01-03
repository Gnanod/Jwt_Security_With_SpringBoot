package jwt.controller;

import jwt.Entity.User;
import jwt.service.UserService;
import jwt.util.AuthUtil;
import jwt.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RestController
@CrossOrigin
@RequestMapping(value = CommonConstants.ROUTE_USER_MAIN)
public class UserController extends GlobalMethodSecurityConfiguration{

    @Autowired
    private UserService userService;
    @Autowired
    private AuthUtil authUtil;
    public static final Logger log = Logger.getLogger(UserController.class.getName());

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value =CommonConstants.ROUTE_CREATE)
    public ResponseEntity<?> addUser(@RequestBody User user) {
        return setUserDetails(user);
    }

    public ResponseEntity<?> setUserDetails(User user) {
        user.setPassword(authUtil.encryptPassword(user.getPassword())); //set the encrypted password
        User userStatus = userService.saveUser(user);
        userStatus.setPassword("");
        return new ResponseEntity<Object>(userStatus, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping(value = CommonConstants.ROUTE_ALL)
    public List<User> getAllRateCardItems() {
        return userService.getAllUsers();
    }


}
