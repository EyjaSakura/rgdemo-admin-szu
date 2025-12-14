package com.szu.admin.service;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateContentDTO;
import com.szu.admin.dto.query.ContentQueryDTO;
import com.szu.admin.vo.ContentVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ContentService {
    PageResult<ContentVO> listContents(ContentQueryDTO dto);
    void changeStatus(ChangeStatusDTO dto);
    void changeDeleted(ChangeStatusDTO dto);
    ContentVO detail(Long id);
    void updateContent(UpdateContentDTO dto);
}
