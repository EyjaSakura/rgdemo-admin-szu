package com.szu.admin.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AdminUser {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private Integer status;     // 1启用 0禁用
    private String banReason;
    private Integer isDeleted;  // 1已删除 0未删除
    private Date createdAt;
    private Date updatedAt;
    private Integer rootPriv;   // 1 = root 0 = 非 root
}