package com.szu.admin.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Content {
    private Long id;
    private String title;
    private String coverImage;
    private String contentBody;
    private Long authorId;
    private Integer viewCount;
    private Integer commentCount;
    private Double avgRating;
    private Integer ratingCount;
    private Integer status;     // 1正常 0禁用
    private Integer isDeleted;  // 1已删除 0未删除
    private String comment;     // JSON 字符串
    private Date createdAt;
    private Date updatedAt;
}