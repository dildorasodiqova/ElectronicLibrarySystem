package uz.uzinfocom.electroniclibrarysystem.service.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.PasswordUpdateRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.UserRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.UserResponse;
import uz.uzinfocom.electroniclibrarysystem.config.jwt.JwtUtil;
import uz.uzinfocom.electroniclibrarysystem.entity.UserEntity;
import uz.uzinfocom.electroniclibrarysystem.enums.Roles;
import uz.uzinfocom.electroniclibrarysystem.exception.ExceptionWithStatusCode;
import uz.uzinfocom.electroniclibrarysystem.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;



    @Override
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found"));
    }

    @Override
    public ResponseEntity<String> login(String username, String password) {
        var user =  userRepository.findByUsername(username).orElseThrow(()-> new ExceptionWithStatusCode(400, "User not found"));
        var isMatch =passwordEncoder.matches(password,user.getPassword());
        if (!isMatch){
            throw new ExceptionWithStatusCode(400, "password is wrong");
        }

        return ResponseEntity.ok(jwtUtil.generateToken(
                userRepository.findByUsernameAndDeletedAtIsNull(username)
                        .orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found with username " + username))));

    }

    @Override
    public ResponseEntity<UserResponse> register(UserRequest user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new ExceptionWithStatusCode(400, "This username already exists");
        }
        UserEntity userEntity1 = user.create();
        userEntity1.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntity1);
        return ResponseEntity.ok(new UserResponse().convert(userEntity1));
    }

    @Override
    public ResponseEntity<UserResponse> updatePassword(Long userId, PasswordUpdateRequest request) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found "));

        if (!passwordEncoder.matches(request.getOldPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("Old password is incorrect");
        }

        userEntity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(userEntity);
        return ResponseEntity.ok(new UserResponse().convert(userEntity));
    }


    @Override
    public ResponseEntity<UserResponse> changeRole(Long id, Roles role) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found"));
        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.ok(new UserResponse().convert(user));
    }

    public ResponseEntity<UserResponse> getById(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new ExceptionWithStatusCode(404, "User not found"));
        return ResponseEntity.ok(new UserResponse().convert(user));
    }
}

