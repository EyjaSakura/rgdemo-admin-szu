package com.szu.admin.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
}