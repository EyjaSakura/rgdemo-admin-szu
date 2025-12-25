package com.szu.admin.controller.user;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateClientUserDTO;
import com.szu.admin.dto.query.UserQueryDTO;
import com.szu.admin.service.user.ClientUserService;
import com.szu.admin.vo.user.ClientUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-user")
public class ClientUserController {

    @Autowired
    private ClientUserService clientUserService;

    // 可筛选是否删除/是否被禁用/指定模糊名称/排序字段/排序方向的小程序用户分页搜索列表
    @PostMapping("/list")
    public Result<PageResult<ClientUserVO>> list(
            @RequestBody UserQueryDTO dto) {
        return Result.ok(clientUserService.listClientUsers(dto));
    }

    // 更改用户信息
    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateClientUserDTO dto) {
        clientUserService.updateClientUser(dto);
        return Result.ok();
    }

    // 启用/禁用用户
    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        clientUserService.changeClientUserStatus(dto);
        return Result.ok();
    }

    // 删除/恢复用户
    @PostMapping("/change-deleted")
    public Result<?> changeDeleted(@RequestBody ChangeDeletedDTO dto) {
        clientUserService.changeClientUserDeleted(dto);
        return Result.ok();
    }

    // 查看用户详细信息
    @GetMapping("/detail/{id}")
    public Result<ClientUserVO> detail(@PathVariable Long id) {
        return Result.ok(clientUserService.detailClientUser(id));
    }
}
