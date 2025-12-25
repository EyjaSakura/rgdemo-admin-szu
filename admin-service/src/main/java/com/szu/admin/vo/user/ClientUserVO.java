package com.szu.admin.vo.user;

import lombok.Data;

import java.util.Date;

@Data
public class ClientUserVO {
    private Long id;
    private String name;
    private String avatar;
    private String sex;
    private String area;
    private String signature;
    private Integer status;
    private String banReason;
    private Integer isDeleted;
    private Date createTime;
    private Date updateTime;
    private Date lastLoginTime;
}
