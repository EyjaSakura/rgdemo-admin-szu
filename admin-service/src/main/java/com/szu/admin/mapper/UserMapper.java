package com.szu.admin.mapper;

import com.szu.admin.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> listUsers(@Param("isDeleted") Integer isDeleted, @Param("role") Integer role);

    User findByUsername(@Param("username") String username);

    int insert(User user);

    int update(User user);

    User findById(@Param("id") Long id);

    User findByPhone(@Param("phone") String phone);

    long countUsers(@Param("isDeleted") Integer isDeleted, @Param("role") Integer role);
}