package com.szu.admin.vo.user;

import lombok.Data;

import java.util.Date;

@Data
public class AdminUserVO {
    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String phone;
    private Integer status;
    private Date createdAt;
}
