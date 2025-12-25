package com.szu.admin.service.user;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateClientUserDTO;
import com.szu.admin.dto.query.UserQueryDTO;
import com.szu.admin.vo.user.ClientUserVO;

public interface ClientUserService {
    PageResult<ClientUserVO> listClientUsers(UserQueryDTO dto);
    void updateClientUser(UpdateClientUserDTO dto);
    void changeClientUserStatus(ChangeStatusDTO dto);
    void changeClientUserDeleted(ChangeDeletedDTO dto);
    ClientUserVO detailClientUser(Long id);
}
