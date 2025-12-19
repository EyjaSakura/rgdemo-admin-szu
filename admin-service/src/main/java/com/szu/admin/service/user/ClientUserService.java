package com.szu.admin.service.user;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateClientUserDTO;
import com.szu.admin.vo.user.ClientUserVO;

public interface ClientUserService {
    PageResult<ClientUserVO> listClientUsers(Integer status, Integer isDeleted, int page, int size);
    void updateClientUser(UpdateClientUserDTO dto);
    void changeClientUserStatus(ChangeStatusDTO dto);
    void changeClientUserIsDeleted(ChangeStatusDTO dto);
    ClientUserVO detailClientUser(Long id);
}
