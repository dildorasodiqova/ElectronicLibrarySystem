package uz.uzinfocom.electroniclibrarysystem.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.LoginDto;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.PasswordUpdateRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.req.UserRequest;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.UserResponse;
import uz.uzinfocom.electroniclibrarysystem.service.userservice.UserService;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PermitAll
    @PostMapping("/login")
    public ResponseEntity<String> login( @RequestBody LoginDto loginDto) {
        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @PermitAll
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid  @RequestBody UserRequest user) {
        return userService.register(user);
    }


    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update-password/{id}")
    public ResponseEntity<UserResponse> updatePassword(@PathVariable Long id, @RequestBody PasswordUpdateRequest request) {
        return userService.updatePassword(id, request);
    }
}
