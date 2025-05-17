package uz.uzinfocom.electroniclibrarysystem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.UserRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.UserResponse;
import uz.uzinfocom.electroniclibrarysystem.entity.User;
import uz.uzinfocom.electroniclibrarysystem.service.userservice.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody UserRequest.LoginDto loginDto) {
        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid  @RequestBody UserRequest user) {
        return userService.register(user);
    }


    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update-password/{id}")
    public ResponseEntity<UserResponse> updatePassword(@PathVariable Long id, @RequestBody UserRequest.PasswordUpdateRequest request) {
        return userService.updatePassword(id, request);
    }
}
