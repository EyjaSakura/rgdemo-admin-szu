package com.szu.admin.dto;

import lombok.Data;

@Data
public class UpdateContentDTO {
    private Long id;
    private String title;
    private String coverImage;
    private String contentBody;
    private Integer viewCount;
    private Integer commentCount;
    private String comments;
    private String tags;
    private String likes;
    private String images;
}