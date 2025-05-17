package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {
    @NotBlank(message = "Full name must not be empty")
    String fullName;

    @NotBlank(message = "Username must not be empty")
    String username;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;

    public User create() {
        User user = new User();
        user.setCreatedAt(LocalDateTime.now().now());
        user.setUsername(getUsername());
        user.setFullName(getFullName());
        return user;

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class LoginDto {
        private String username;
        private String password;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class PasswordUpdateRequest {
        private String oldPassword;
        private String newPassword;
    }

}
