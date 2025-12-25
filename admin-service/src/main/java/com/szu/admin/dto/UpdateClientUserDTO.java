package com.szu.admin.dto;

import lombok.Data;

@Data
public class UpdateClientUserDTO {
    private Long id;
    private String name;
    private String avatar;
    private String sex;
    private String area;
    private String signature;
}
