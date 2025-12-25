package com.szu.admin.controller.user;

import com.szu.admin.common.PageResult;
import com.szu.admin.common.Result;
import com.szu.admin.common.annotation.RequireRoot;
import com.szu.admin.common.annotation.RequireSelf;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateAdminUserDTO;
import com.szu.admin.dto.query.UserQueryDTO;
import com.szu.admin.service.user.AdminUserService;
import com.szu.admin.vo.user.AdminUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-user")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    // 可筛选是否删除/是否被禁用/指定模糊名称/排序字段/排序方向的管理员分页搜索列表
    @RequireRoot
    @PostMapping("/list")
    public Result<PageResult<AdminUserVO>> list(
            @RequestBody UserQueryDTO dto) {
        return Result.ok(adminUserService.listAdminUsers(dto));
    }

    // 一键创建初始管理员
    @RequireRoot
    @PostMapping("/add-default")
    public Result<?> addDefault() {
        adminUserService.addDefaultAdminUser();
        return Result.ok();
    }

    // 更改管理员信息
    @RequireSelf("id")
    @PostMapping("/update")
    public Result<?> update(@RequestBody UpdateAdminUserDTO dto) {
        adminUserService.updateAdminUser(dto);
        return Result.ok();
    }

    // 启用/禁用管理员
    @RequireRoot
    @PostMapping("/change-status")
    public Result<?> changeStatus(@RequestBody ChangeStatusDTO dto) {
        adminUserService.changeAdminUserStatus(dto);
        return Result.ok();
    }

    // 删除/恢复管理员
    @PostMapping("/change-deleted")
    public Result<?> changeDeleted(@RequestBody ChangeDeletedDTO dto) {
        adminUserService.changeAdminUserDeleted(dto);
        return Result.ok();
    }

    // 查看管理员详细信息
    @RequireRoot
    @GetMapping("/detail/{id}")
    public Result<AdminUserVO> detail(@PathVariable Long id) {
        return Result.ok(adminUserService.detailAdminUser(id));
    }

    // 登陆注册
    // 注册操作：新管理员 = 超级管理员添加 + 管理员改信息
    // 登录（拦截器放行）
    @PostMapping("/login-admin")
    public Result<?> loginAdmin(@RequestBody LoginAdminDTO dto) {
        String token = adminUserService.loginAdminUser(dto);
        return Result.ok(token);
    }
}