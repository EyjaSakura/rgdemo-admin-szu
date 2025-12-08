package com.szu.admin.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private Integer status;      // 1启用 0禁用
    private Integer isDeleted;   // 1删除 0未删除
    private Integer role;        // 1管理员 0普通
    private Date createdAt;
    private Date updatedAt;
}