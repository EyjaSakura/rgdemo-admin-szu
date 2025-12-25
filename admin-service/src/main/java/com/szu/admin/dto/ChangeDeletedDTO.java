package com.szu.admin.dto;

import lombok.Data;

@Data
public class ChangeDeletedDTO {
    private Long id;
    private Integer isDeleted;
}
