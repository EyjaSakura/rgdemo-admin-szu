package com.szu.admin.service.user;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateAdminUserDTO;
import com.szu.admin.dto.query.UserQueryDTO;
import com.szu.admin.vo.user.AdminUserVO;
import org.apache.catalina.User;

public interface AdminUserService {

    // 可筛选是否删除/是否被禁用/指定模糊名称/排序字段/排序方向的管理员分页搜索列表
    PageResult<AdminUserVO> listAdminUsers(UserQueryDTO dto);

    // 一键创建初始管理员
    void addDefaultAdminUser();

    // 更改管理员信息
    void updateAdminUser(UpdateAdminUserDTO dto);

    // 启用/禁用管理员
    void changeAdminUserStatus(ChangeStatusDTO dto);

    // 删除/恢复管理员
    void changeAdminUserDeleted(ChangeDeletedDTO dto);

    // 查看管理员详细信息
    AdminUserVO detailAdminUser(Long id);

    // 管理员登录
    String loginAdminUser(LoginAdminDTO dto);
}
