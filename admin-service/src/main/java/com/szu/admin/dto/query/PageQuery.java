package com.szu.admin.dto.query;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

// 统一分页父类
@Data
public class PageQuery {

    @Min(1)
    private Integer page = 1;

    @Min(1)
    @Max(100)
    private Integer size = 10;

    private String sortBy = "created_at";

    private String sortOrder = "desc";
}
