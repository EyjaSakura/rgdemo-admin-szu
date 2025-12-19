package com.szu.admin.service.user.impl;

import com.szu.admin.common.PageResult;
import com.szu.admin.domain.AdminUser;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateAdminUserDTO;
import com.szu.admin.mapper.user.AdminUserMapper;
import com.szu.admin.service.user.AdminUserService;
import com.szu.admin.utils.JwtUtils;
import com.szu.admin.utils.PhoneUtils;
import com.szu.admin.utils.StringUtils;
import com.szu.admin.vo.user.AdminUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public PageResult<AdminUserVO> listAdminUsers(Integer status, int page, int size) {
        List<AdminUser> list = adminUserMapper.listAdminUsers(status);
        long total = adminUserMapper.countAdminUsers(status);
        List<AdminUserVO> vos = list.stream().skip((long)(page-1)*size).limit(size).map(this::toVO).collect(Collectors.toList());
        return new PageResult<>(total, vos);
    }

    @Override
    public void addDefaultAdminUser() {
        AdminUser u = new AdminUser();
        int rand = (int)(Math.random() * 900000) + 100000;
        u.setUsername("admin_" + rand);
        u.setPassword("123456");
        u.setNickname("admin");
        u.setAvatar(null);
        u.setPhone(null);
        u.setStatus(1);
        adminUserMapper.insertAdminUser(u);
    }

    @Override
    public void updateAdminUser(UpdateAdminUserDTO dto) {
        if (dto.getId() == null) throw new RuntimeException("id 不能为空");
        AdminUser old = adminUserMapper.findAdminUserById(dto.getId());
        if (old == null) throw new RuntimeException("用户不存在");

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            AdminUser byName = adminUserMapper.findAdminUserByUsername(dto.getUsername());
            if (byName != null && !byName.getId().equals(dto.getId())) throw new RuntimeException("用户名已存在");
        }

        if (dto.getNickname() != null && !dto.getNickname().matches("[A-Za-z0-9]*")) {
            throw new RuntimeException("昵称只能是英文字母或数字");
        }

        if (dto.getPhone() != null && !PhoneUtils.isValidPhone(dto.getPhone())) {
            throw new RuntimeException("手机号格式不合法");
        }

        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            AdminUser byPhone = adminUserMapper.findAdminUserByPhone(dto.getPhone());
            if (byPhone != null && !byPhone.getId().equals(dto.getId())) throw new RuntimeException("手机号已被使用");
        }

        AdminUser u = new AdminUser();
        u.setId(dto.getId());
        u.setUsername(StringUtils.blankToNull(dto.getUsername()));
        u.setPassword(StringUtils.blankToNull(dto.getPassword()));
        u.setNickname(StringUtils.blankToNull(dto.getNickname()));
        u.setAvatar(StringUtils.blankToNull(dto.getAvatar()));
        u.setPhone(StringUtils.blankToNull(dto.getPhone()));

        adminUserMapper.updateAdminUser(u);
    }

    @Override
    public void changeAdminUserStatus(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        AdminUser u = new AdminUser();
        u.setId(dto.getId());
        u.setStatus(dto.getValue());
        adminUserMapper.updateAdminUser(u);
    }

    @Override
    public AdminUserVO detailAdminUser(Long id) {
        AdminUser u = adminUserMapper.findAdminUserById(id);
        return toVO(u);
    }

    @Override
    public String loginAdminUser(LoginAdminDTO dto) {
        if (dto.getUsername() == null || dto.getPassword() == null) throw new RuntimeException("参数不完整");
        AdminUser u = adminUserMapper.findAdminUserByUsername(dto.getUsername());
        if (u == null) throw new RuntimeException("用户不存在");
        if (!u.getPassword().equals(dto.getPassword())) throw new RuntimeException("密码不正确");
        // 登录成功，生成 token
        return JwtUtils.generateToken(u.getId(), u.getUsername(), u.getRootPriv());
    }

    private AdminUserVO toVO(AdminUser u) {
        if (u == null) return null;
        AdminUserVO vo = new AdminUserVO();
        BeanUtils.copyProperties(u, vo);
        return vo;
    }
}