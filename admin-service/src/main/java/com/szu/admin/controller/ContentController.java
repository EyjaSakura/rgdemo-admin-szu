package com.szu.admin.controller;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateContentDTO;
import com.szu.admin.service.ContentService;
import com.szu.admin.vo.ContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/list")
    public Result<PageResult<ContentVO>> list(
            @RequestParam(required = false) Integer isDeleted,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.ok(contentService.listContents(isDeleted, status, page, size));
    }

    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        contentService.changeStatus(dto);
        return Result.ok();
    }

    @PostMapping("/change-deleted")
    public Result<?> changeDeleted(@RequestBody ChangeStatusDTO dto) {
        contentService.changeDeleted(dto);
        return Result.ok();
    }

    @GetMapping("/detail/{id}")
    public Result<ContentVO> detail(@PathVariable Long id) {
        return Result.ok(contentService.detail(id));
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateContentDTO dto) {
        contentService.updateContent(dto);
        return Result.ok();
    }
}
