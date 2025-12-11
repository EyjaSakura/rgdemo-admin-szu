package com.szu.admin.controller;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateAdminUserDTO;
import com.szu.admin.service.AdminUserService;
import com.szu.admin.vo.AdminUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/list")
    public Result<PageResult<AdminUserVO>> list(
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.ok(adminUserService.listAdminUsers(status, page, size));
    }

    @PostMapping("/add-default")
    public Result<?> addDefault() {
        adminUserService.addDefaultAdminUser();
        return Result.ok();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateAdminUserDTO dto) {
        adminUserService.updateAdminUser(dto);
        return Result.ok();
    }

    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        adminUserService.changeAdminUserStatus(dto);
        return Result.ok();
    }

    @GetMapping("/detail/{id}")
    public Result<AdminUserVO> detail(@PathVariable Long id) {
        return Result.ok(adminUserService.detailAdminUser(id));
    }

    // 登陆注册
    // 注册找其他管理员手动生成默认（什么内推制度）
    // 登录
    @PostMapping("/login-admin")
    public Result<?> loginAdmin(@RequestBody LoginAdminDTO dto) {
        String token = adminUserService.loginAdminUser(dto);
        return Result.ok(token);
    }
}