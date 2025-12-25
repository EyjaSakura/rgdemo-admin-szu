package com.szu.admin.service.user;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateClientUserDTO;
import com.szu.admin.dto.query.UserQueryDTO;
import com.szu.admin.vo.user.ClientUserVO;

public interface ClientUserService {

    // 可筛选是否删除/是否被禁用/指定模糊名称/排序字段/排序方向的小程序用户分页搜索列表
    PageResult<ClientUserVO> listClientUsers(UserQueryDTO dto);

    // 更改用户信息
    void updateClientUser(UpdateClientUserDTO dto);

    // 启用/禁用用户
    void changeClientUserStatus(ChangeStatusDTO dto);

    // 删除/恢复用户
    void changeClientUserDeleted(ChangeDeletedDTO dto);

    // 查看用户详细信息
    ClientUserVO detailClientUser(Long id);
}
