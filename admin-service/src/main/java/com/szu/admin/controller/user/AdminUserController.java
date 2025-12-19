package com.szu.admin.controller.user;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.common.annotation.RequireRoot;
import com.szu.admin.common.annotation.RequireSelf;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateAdminUserDTO;
import com.szu.admin.service.user.AdminUserService;
import com.szu.admin.vo.user.AdminUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @RequireRoot
    @GetMapping("/list")
    public Result<PageResult<AdminUserVO>> list(
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.ok(adminUserService.listAdminUsers(status, page, size));
    }

    @RequireRoot
    @PostMapping("/add-default")
    public Result<?> addDefault() {
        adminUserService.addDefaultAdminUser();
        return Result.ok();
    }

    // 资源归属校验，无需角色权限验证（无需root）
    @RequireSelf("id")
    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateAdminUserDTO dto) {
        adminUserService.updateAdminUser(dto);
        return Result.ok();
    }

    @RequireRoot
    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        adminUserService.changeAdminUserStatus(dto);
        return Result.ok();
    }

    @RequireRoot
    @GetMapping("/detail/{id}")
    public Result<AdminUserVO> detail(@PathVariable Long id) {
        return Result.ok(adminUserService.detailAdminUser(id));
    }

    // 登陆注册
    // 注册操作：新管理员 = 超级管理员添加 + 管理员改信息
    // 登录（无需Root权限）
    @PostMapping("/login-admin")
    public Result<?> loginAdmin(@RequestBody LoginAdminDTO dto) {
        String token = adminUserService.loginAdminUser(dto);
        return Result.ok(token);
    }
}