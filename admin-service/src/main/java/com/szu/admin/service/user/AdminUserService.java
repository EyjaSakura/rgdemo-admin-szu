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
    PageResult<AdminUserVO> listAdminUsers(UserQueryDTO dto);
    void addDefaultAdminUser();
    void updateAdminUser(UpdateAdminUserDTO dto);
    void changeAdminUserStatus(ChangeStatusDTO dto);
    void changeAdminUserDeleted(ChangeDeletedDTO dto);
    AdminUserVO detailAdminUser(Long id);
    String loginAdminUser(LoginAdminDTO dto);
}
