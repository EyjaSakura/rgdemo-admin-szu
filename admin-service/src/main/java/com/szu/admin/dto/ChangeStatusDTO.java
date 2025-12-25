package com.szu.admin.dto;

import lombok.Data;

@Data
public class ChangeStatusDTO {
    private Long id;
    private Integer status;
    private String banReason;
}
