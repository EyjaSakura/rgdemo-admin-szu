package com.szu.admin.mapper.user;

import com.szu.admin.domain.ClientUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientUserMapper {
    List<ClientUser> listClientUsers(@Param("status") Integer status, @Param("isDeleted") Integer isDeleted);

    ClientUser findClientUserByName(@Param("name") String name);

    ClientUser findClientUserById(@Param("id") Long id);

    ClientUser findClientUserByOpenId(@Param("openid") String openid);

    int updateClientUser(ClientUser adminUser);

    long countClientUsers(@Param("status") Integer status, @Param("isDeleted") Integer isDeleted);
}
