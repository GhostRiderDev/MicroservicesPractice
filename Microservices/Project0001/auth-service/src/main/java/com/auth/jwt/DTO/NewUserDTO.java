package com.auth.jwt.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NewUserDTO {
    private String username;
    private String password;
    private String role;
}
