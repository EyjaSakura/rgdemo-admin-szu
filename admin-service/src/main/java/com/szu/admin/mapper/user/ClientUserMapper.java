package com.szu.admin.mapper.user;

import com.szu.admin.domain.ClientUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientUserMapper {
    List<ClientUser> listClientUsers(@Param("isDeleted") Integer isDeleted, @Param("status") Integer status, @Param("name") String name, @Param("sortBy") String sortBy, @Param("sortOrder") String sortOrder);

    ClientUser findClientUserByName(@Param("name") String name);

    ClientUser findClientUserById(@Param("id") Long id);

    ClientUser findClientUserByOpenId(@Param("openid") String openid);

    int updateClientUser(ClientUser adminUser);

    long countClientUsers(@Param("isDeleted") Integer isDeleted, @Param("status") Integer status, @Param("name") String name);
}
