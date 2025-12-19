package com.szu.admin.controller.content;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
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

    @PostMapping("/list")
    public Result<PageResult<ContentVO>> list(
            @RequestBody ContentQueryDTO dto) {
        return Result.ok(contentService.listContents(dto));
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
