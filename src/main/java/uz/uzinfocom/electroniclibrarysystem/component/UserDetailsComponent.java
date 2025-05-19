package uz.uzinfocom.electroniclibrarysystem.component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.UserRepository;

import java.util.List;

@Component
public class UserDetailsComponent implements UserDetailsService{
    private final UserRepository userRepository;

    public UserDetailsComponent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user =  userRepository.findByUsername(username).orElseThrow(()->new ExceptionWithStatusCode(400,"user.not.found"));

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));


        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
