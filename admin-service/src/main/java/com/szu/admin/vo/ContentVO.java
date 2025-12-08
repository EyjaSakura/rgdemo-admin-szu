package com.szu.admin.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ContentVO {
    private Long id;
    private String title;
    private String coverImage;
    private String contentBody;
    private Long authorId;
    private Integer viewCount;
    private Integer commentCount;
    private Double avgRating;
    private Integer ratingCount;
    private Integer status;
    private Integer isDeleted;
    private String comment;
    private Date createdAt;
}