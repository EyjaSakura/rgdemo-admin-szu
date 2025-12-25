package com.szu.admin.service.content;

import com.szu.admin.common.PageResult;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateContentDTO;
import com.szu.admin.dto.query.ContentQueryDTO;
import com.szu.admin.vo.content.ContentVO;

public interface ContentService {

    // 可筛选是否删除/是否被禁用/指定标签/指定关键词/排序字段/排序方向的内容分页搜索列表
    PageResult<ContentVO> listContents(ContentQueryDTO dto);

    // 启用/禁用内容
    void changeStatus(ChangeStatusDTO dto);

    // 删除/恢复内容
    void changeDeleted(ChangeDeletedDTO dto);

    // 查看内容详细信息
    ContentVO detail(Long id);

    // 更改内容信息
    void updateContent(UpdateContentDTO dto);
}
