package com.szu.admin.dto;

import lombok.Data;

@Data
public class UpdateContentDTO {
    private Long id;
    private String title;
    private String coverImage;
    private String contentBody;
    private Integer status;
    private Integer isDeleted;
}