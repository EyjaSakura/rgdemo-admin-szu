package com.szu.admin.mapper.content;

import com.szu.admin.domain.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ContentMapper {
    List<Content> listContents(@Param("isDeleted") Integer isDeleted, @Param("status") Integer status, @Param("tagIds") List<Integer> tagIds);

    Content findById(@Param("id") Long id);

    int insert(Content content);

    int update(Content content);

    long countContents(@Param("isDeleted") Integer isDeleted, @Param("status") Integer status, @Param("tagIds") List<Integer> tagIds);
}
