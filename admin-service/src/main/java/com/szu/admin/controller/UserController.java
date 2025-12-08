package com.szu.admin.controller;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateUserDTO;
import com.szu.admin.service.UserService;
import com.szu.admin.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result<PageResult<UserVO>> list(
            @RequestParam(required = false) Integer isDeleted,
            @RequestParam(required = false) Integer role,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.ok(userService.listUsers(isDeleted, role, page, size));
    }

    @PostMapping("/add-default")
    public Result<?> addDefault() {
        userService.addDefaultUser();
        return Result.ok();
    }

    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateUserDTO dto) {
        userService.updateUser(dto);
        return Result.ok();
    }

    @PostMapping("/change-role")
    public Result<?> changeRole(@RequestBody ChangeStatusDTO dto) {
        userService.changeRole(dto);
        return Result.ok();
    }

    @PostMapping("/change-deleted")
    public Result<?> changeDeleted(@RequestBody ChangeStatusDTO dto) {
        userService.changeDeleted(dto);
        return Result.ok();
    }

    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        userService.changeStatus(dto);
        return Result.ok();
    }

    @GetMapping("/detail/{id}")
    public Result<UserVO> detail(@PathVariable Long id) {
        return Result.ok(userService.detail(id));
    }

    // 登陆注册
    // 注册流程采用 1.创建默认账户 2.更改权限
    // 登录
    @PostMapping("/login-admin")
    public Result<?> loginAdmin(@RequestBody LoginAdminDTO dto) {
        String token = userService.loginAdmin(dto);
        return Result.ok(token);
    }
}