package com.szu.admin.controller.content;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateContentDTO;
import com.szu.admin.dto.query.ContentQueryDTO;
import com.szu.admin.service.content.ContentService;
import com.szu.admin.vo.content.ContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    // 可筛选是否删除/是否被禁用/指定标签/指定关键词/排序字段/排序方向的内容分页搜索列表
    @PostMapping("/list")
    public Result<PageResult<ContentVO>> list(
            @RequestBody ContentQueryDTO dto) {
        return Result.ok(contentService.listContents(dto));
    }

    // 启用/禁用内容
    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        contentService.changeStatus(dto);
        return Result.ok();
    }

    // 删除/恢复内容
    @PostMapping("/change-deleted")
    public Result<?> changeDeleted(@RequestBody ChangeDeletedDTO dto) {
        contentService.changeDeleted(dto);
        return Result.ok();
    }

    // 查看内容详细信息
    @GetMapping("/detail/{id}")
    public Result<ContentVO> detail(@PathVariable Long id) {
        return Result.ok(contentService.detail(id));
    }

    // 更改内容信息
    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateContentDTO dto) {
        contentService.updateContent(dto);
        return Result.ok();
    }
}
