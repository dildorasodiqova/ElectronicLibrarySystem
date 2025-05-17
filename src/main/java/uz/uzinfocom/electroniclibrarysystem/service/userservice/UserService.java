package uz.uzinfocom.electroniclibrarysystem.service.userservice;

import org.springframework.http.ResponseEntity;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.UserRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.UserResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.User;

public interface UserService {
    User findById(Long userId);

    ResponseEntity<String> login(String username, String password);

    ResponseEntity<UserResponse> register(UserRequest user);

    ResponseEntity<UserResponse> updatePassword(Long userId, UserRequest.PasswordUpdateRequest request);
}
