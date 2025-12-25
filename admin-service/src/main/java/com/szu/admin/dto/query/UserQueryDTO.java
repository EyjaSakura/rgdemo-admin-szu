package com.szu.admin.dto.query;

import lombok.Data;

@Data
public class UserQueryDTO extends PageQuery {
    private Integer isDeleted;
    private Integer status;
    private String name;
}
