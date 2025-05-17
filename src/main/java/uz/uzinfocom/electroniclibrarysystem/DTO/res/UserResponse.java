package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.User;
import uz.uzinfocom.electroniclibrarysystem.enums.Roles;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String fullName;
    String username;
    Roles role;

    public UserResponse convert(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole());
        return response;
    }
}
