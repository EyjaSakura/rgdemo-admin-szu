package com.szu.admin.service;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateContentDTO;
import com.szu.admin.vo.ContentVO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ContentService {
    PageResult<ContentVO> listContents(Integer isDeleted, Integer status, int page, int size);
    void changeStatus(ChangeStatusDTO dto);
    void changeDeleted(ChangeStatusDTO dto);
    ContentVO detail(Long id);
    void updateContent(UpdateContentDTO dto);
}
