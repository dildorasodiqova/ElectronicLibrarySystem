package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.UserEntity;
import uz.uzinfocom.electroniclibrarysystem.enums.Roles;

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

    public UserEntity create() {
        UserEntity userEntity = new UserEntity();
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUsername(getUsername());
        userEntity.setFullName(getFullName());
        userEntity.setRole(Roles.USER);
        return userEntity;

    }




}
