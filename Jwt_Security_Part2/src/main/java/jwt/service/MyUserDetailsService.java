package jwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles = null;
        jwt.Entity.User user = userService.getUserByEmail(userName);
        if (user != null) {
            if (user.getRole().equals("Admin")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (user.getRole().equals("Operational Manager")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (user.getRole().equals("HelpDesk User")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (user.getRole().equals("Property Manager")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (user.getRole().equals("Security")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            } else if (user.getRole().equals("Maintainer")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            } else if (user.getRole().equals("Engineer")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            } else if (user.getRole().equals("Owner")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            } else if (user.getRole().equals("Tenant")) {
                roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new User(user.getEmail(), user.getPassword(), roles);

        }
        throw new UsernameNotFoundException("User not found with the name " + userName);
    }


}
