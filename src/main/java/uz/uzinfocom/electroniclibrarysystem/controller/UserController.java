package uz.uzinfocom.electroniclibrarysystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.uzinfocom.electroniclibrarysystem.DTO.res.UserResponse;
import uz.uzinfocom.electroniclibrarysystem.enums.Roles;
import uz.uzinfocom.electroniclibrarysystem.service.userservice.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PreAuthorize("hasAnyRole('USER', 'OPERATOR')")
    @PutMapping("/change-role/{id}")
    public ResponseEntity<UserResponse> changeRole(@PathVariable Long id, Roles role){
        return userService.changeRole(id, role);
    }

    @PreAuthorize("hasAnyRole('USER', 'OPERATOR','ADMIN')")
    @GetMapping("/get-by-id/{id}")
    ResponseEntity<UserResponse> getById(Long id){
        return userService.getById(id);
    }
}
