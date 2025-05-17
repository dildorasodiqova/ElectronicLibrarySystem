package uz.uzinfocom.electroniclibrarysystem.DTO.res;

import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.uzinfocom.electroniclibrarysystem.entity.UserEntity;
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

    public UserResponse convert(UserEntity userEntity) {
        UserResponse response = new UserResponse();
        response.setId(userEntity.getId());
        response.setFullName(userEntity.getFullName());
        response.setUsername(userEntity.getUsername());
        response.setRole(userEntity.getRole());
        return response;
    }
}
