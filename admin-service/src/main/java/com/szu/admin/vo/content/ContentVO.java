package com.szu.admin.vo.content;

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
    private Integer status;
    private String banReason;
    private Integer isDeleted;
    private String comments;
    private String tags;
    private String likes;
    private String images;
    private Date createdAt;
}