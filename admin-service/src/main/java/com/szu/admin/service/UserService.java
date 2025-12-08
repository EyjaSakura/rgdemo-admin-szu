package com.szu.admin.service;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateUserDTO;
import com.szu.admin.vo.UserVO;

public interface UserService {
    PageResult<UserVO> listUsers(Integer isDeleted, Integer role, int page, int size);
    void addDefaultUser();
    void updateUser(UpdateUserDTO dto);
    void changeRole(ChangeStatusDTO dto);
    void changeDeleted(ChangeStatusDTO dto);
    void changeStatus(ChangeStatusDTO dto);
    UserVO detail(Long id);
    String loginAdmin(LoginAdminDTO dto);
}
