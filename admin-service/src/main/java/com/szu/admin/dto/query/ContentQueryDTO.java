package com.szu.admin.dto.query;

import lombok.Data;

import java.util.List;

// 分页内容查询条件
@Data
public class ContentQueryDTO extends PageQuery {
    private Integer isDeleted;
    private Integer status;
    private List<Integer> tagIds;
    private String keyword;
}
