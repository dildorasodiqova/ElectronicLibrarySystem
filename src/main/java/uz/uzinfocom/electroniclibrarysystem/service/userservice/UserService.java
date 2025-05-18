package uz.uzinfocom.electroniclibrarysystem.service.userservice;

import org.springframework.http.ResponseEntity;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.PasswordUpdateRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.UserRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.UserResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.UserEntity;
import uz.uzinfocom.electroniclibrarysystem.enums.Roles;

public interface UserService {
    UserEntity findById(Long userId);

    ResponseEntity<String> login(String username, String password);

    ResponseEntity<UserResponse> register(UserRequest user);

    ResponseEntity<UserResponse> updatePassword(Long userId, PasswordUpdateRequest request);

    ResponseEntity<UserResponse> changeRole(Long id, Roles role);
    ResponseEntity<UserResponse> getById(Long id);
}
