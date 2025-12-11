package com.szu.admin.controller;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateClientUserDTO;
import com.szu.admin.service.ClientUserService;
import com.szu.admin.vo.ClientUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client-user")
public class ClientUserController {

    @Autowired
    private ClientUserService clientUserService;

    @GetMapping("/list")
    public Result<PageResult<ClientUserVO>> list(
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer isDeleted,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.ok(clientUserService.listClientUsers(status, isDeleted, page, size));
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateClientUserDTO dto) {
        clientUserService.updateClientUser(dto);
        return Result.ok();
    }

    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        clientUserService.changeClientUserStatus(dto);
        return Result.ok();
    }

    @PostMapping("/change-deleted")
    public Result<?> changeDeleted(@RequestBody ChangeStatusDTO dto) {
        clientUserService.changeClientUserIsDeleted(dto);
        return Result.ok();
    }

    @GetMapping("/detail/{id}")
    public Result<ClientUserVO> detail(@PathVariable Long id) {
        return Result.ok(clientUserService.detailClientUser(id));
    }
}
