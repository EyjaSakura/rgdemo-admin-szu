package com.szu.admin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String phone;
    private Integer status;
    private Integer isDeleted;
    private Integer role;
    private Date createdAt;
}
