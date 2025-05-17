package uz.uzinfocom.electroniclibrarysystem.DTO.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class PasswordUpdateRequest {
        private String oldPassword;
        private String newPassword;
    }