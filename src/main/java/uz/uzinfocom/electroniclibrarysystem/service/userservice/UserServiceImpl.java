package uz.uzinfocom.electroniclibrarysystem.service.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.UserRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.UserResponse;
import uz.uzinfocom.electroniclibrarysystem.config.jwt.JwtUtil;
import uz.uzinfocom.electroniclibrarysystem.entity.User;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found"));
    }

    @Override
    public ResponseEntity<String> login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return ResponseEntity.ok(jwtUtil.generateToken(userDetails,
                userRepository.findByUsernameAndDeletedAtIsNull(username)
                        .orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found with username " + username))));

    }

    @Override
    public ResponseEntity<UserResponse> register(UserRequest user) {
        if (userRepository.findByUsername(user.getUsername())) {
            throw new ExceptionWithStatusCode(400, "This username already exists");
        }
        User user1 = user.create();
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user1);
        return ResponseEntity.ok(new UserResponse().convert(user1));
    }

    @Override
    public ResponseEntity<UserResponse> updatePassword(Long userId, UserRequest.PasswordUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found "));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new UserResponse().convert(user));
    }
}
