package jwt.controller;

import jwt.util.CommonConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RestController
@CrossOrigin
@RequestMapping(value = CommonConstants.ROUTE_ENGINEER_MAIN)
public class EngineerController extends GlobalMethodSecurityConfiguration{

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping({"/hellouser"})
    public String helloUser(){
        return "Hello User  ENGINEER";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping({"/helloadmin"})
    public String helloAdmin(){
        return "Hello Admin ENGINEER";
    }

}
