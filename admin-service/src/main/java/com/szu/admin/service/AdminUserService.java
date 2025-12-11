package com.szu.admin.service;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateAdminUserDTO;
import com.szu.admin.vo.AdminUserVO;

public interface AdminUserService {
    PageResult<AdminUserVO> listAdminUsers(Integer status, int page, int size);
    void addDefaultAdminUser();
    void updateAdminUser(UpdateAdminUserDTO dto);
    void changeAdminUserStatus(ChangeStatusDTO dto);
    AdminUserVO detailAdminUser(Long id);
    String loginAdminUser(LoginAdminDTO dto);
}
