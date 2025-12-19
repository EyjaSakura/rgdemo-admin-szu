package com.szu.admin.vo.user;

import lombok.Data;

import java.util.Date;

@Data
public class ClientUserVO {
    private Long id;
    private String openid;
    private String name;
    private String avatar;
    private Integer status;
    private Integer isDeleted;
    private Date createTime;
}
