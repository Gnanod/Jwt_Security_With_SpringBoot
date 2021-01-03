package jwt.controller;



import jwt.Entity.Permission;
import jwt.dto.PermissionDto;
import jwt.service.PermissionService;
import jwt.util.CommonConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RestController
@CrossOrigin
@RequestMapping(value = CommonConstants.ROUTE_PERMISSION_MAIN)
public class PermissonController extends GlobalMethodSecurityConfiguration {


    @Autowired
    private PermissionService permissionService;
    public static final Logger log = Logger.getLogger(PermissonController.class.getName());


    @PostMapping(value = CommonConstants.ROUTE_CREATE)
    public ResponseEntity<?> savePermission(@RequestBody List<Permission> permission) {

            boolean result = permissionService.savePermission(permission);
            return new ResponseEntity<Object>(result, HttpStatus.OK);

    }


    @GetMapping(value = CommonConstants.ROUTE_SINGLE)
    public List<PermissionDto> getPermissionByUserId(@PathVariable String id) {
        return permissionService.getPermissionByUserId(id);
    }


}
