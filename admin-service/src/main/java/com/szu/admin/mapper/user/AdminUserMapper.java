package com.szu.admin.mapper.user;

import com.szu.admin.domain.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    List<AdminUser> listAdminUsers(@Param("isDeleted") Integer isDeleted, @Param("status") Integer status, @Param("name") String name, @Param("sortBy") String sortBy, @Param("sortOrder") String sortOrder);

    AdminUser findAdminUserByUsername(@Param("username") String username);

    AdminUser findAdminUserById(@Param("id") Long id);

    AdminUser findAdminUserByPhone(@Param("phone") String phone);

    int insertAdminUser(AdminUser adminUser);

    int updateAdminUser(AdminUser adminUser);

    long countAdminUsers(@Param("isDeleted") Integer isDeleted, @Param("status") Integer status, @Param("name") String name);
}