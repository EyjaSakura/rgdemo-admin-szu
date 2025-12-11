package com.szu.admin.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ClientUser {
    private Long id;
    private String openid;
    private String name;
    private String avatar;
    private Integer status;      // 1启用 0禁用
    private Integer isDeleted;   // 1已删除 0未删除
    private Date createTime;
    private Date updateTime;
}
