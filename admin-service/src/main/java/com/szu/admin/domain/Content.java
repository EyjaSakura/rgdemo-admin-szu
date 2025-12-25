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
    private Integer status;     // 1正常 0禁用
    private String banReason;
    private Integer isDeleted;  // 1已删除 0未删除
    private String comments;     // JSON 字符串
    private String tags;        // JSON
    private String likes;       // JSON
    private String images;      // JSON
    private Date createdAt;
    private Date updatedAt;
}