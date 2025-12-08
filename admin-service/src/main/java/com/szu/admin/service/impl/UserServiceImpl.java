package com.szu.admin.service.impl;

import com.szu.admin.common.PageResult;
import com.szu.admin.domain.User;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.LoginAdminDTO;
import com.szu.admin.dto.UpdateUserDTO;
import com.szu.admin.mapper.UserMapper;
import com.szu.admin.service.UserService;
import com.szu.admin.utils.JwtUtils;
import com.szu.admin.utils.PhoneUtils;
import com.szu.admin.utils.StringUtils;
import com.szu.admin.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult<UserVO> listUsers(Integer isDeleted, Integer role, int page, int size) {
        List<User> list = userMapper.listUsers(isDeleted, role);
        long total = userMapper.countUsers(isDeleted, role);
        List<UserVO> vos = list.stream().skip((long)(page-1)*size).limit(size).map(this::toVO).collect(Collectors.toList());
        return new PageResult<>(total, vos);
    }

    @Override
    public void addDefaultUser() {
        User u = new User();
        int rand = (int)(Math.random() * 900000) + 100000;
        u.setUsername("user_" + rand);
        u.setPassword("123456");
        u.setNickname("user");
        u.setAvatar(null);
        u.setPhone(null);
        u.setStatus(1);
        u.setIsDeleted(0);
        u.setRole(0);
        userMapper.insert(u);
    }

    @Override
    public void updateUser(UpdateUserDTO dto) {
        if (dto.getId() == null) throw new RuntimeException("id 不能为空");
        User old = userMapper.findById(dto.getId());
        if (old == null) throw new RuntimeException("用户不存在");

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            User byName = userMapper.findByUsername(dto.getUsername());
            if (byName != null && !byName.getId().equals(dto.getId())) throw new RuntimeException("用户名已存在");
        }

        if (dto.getNickname() != null && !dto.getNickname().matches("[A-Za-z0-9]*")) {
            throw new RuntimeException("昵称只能是英文字母或数字");
        }

        if (dto.getPhone() != null && !PhoneUtils.isValidPhone(dto.getPhone())) {
            throw new RuntimeException("手机号格式不合法");
        }

        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            User byPhone = userMapper.findByPhone(dto.getPhone());
            if (byPhone != null && !byPhone.getId().equals(dto.getId())) throw new RuntimeException("手机号已被使用");
        }

        User u = new User();
        u.setId(dto.getId());
        u.setUsername(StringUtils.blankToNull(dto.getUsername()));
        u.setPassword(StringUtils.blankToNull(dto.getPassword()));
        u.setNickname(StringUtils.blankToNull(dto.getNickname()));
        u.setAvatar(StringUtils.blankToNull(dto.getAvatar()));
        u.setPhone(StringUtils.blankToNull(dto.getPhone()));

        userMapper.update(u);
    }

    @Override
    public void changeRole(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        User u = new User();
        u.setId(dto.getId());
        u.setRole(dto.getValue());
        userMapper.update(u);
    }

    @Override
    public void changeDeleted(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        User u = new User();
        u.setId(dto.getId());
        u.setIsDeleted(dto.getValue());
        userMapper.update(u);
    }

    @Override
    public void changeStatus(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        User u = new User();
        u.setId(dto.getId());
        u.setStatus(dto.getValue());
        userMapper.update(u);
    }

    @Override
    public UserVO detail(Long id) {
        User u = userMapper.findById(id);
        return toVO(u);
    }

    @Override
    public String loginAdmin(LoginAdminDTO dto) {
        if (dto.getUsername() == null || dto.getPassword() == null) throw new RuntimeException("参数不完整");
        User u =userMapper.findByUsername(dto.getUsername());
        if (u == null) throw new RuntimeException("用户不存在");
        if (!u.getPassword().equals(dto.getPassword())) throw new RuntimeException("密码不正确");
        if (u.getRole() != 1) throw new RuntimeException("权限不足");
        // 登录成功，生成 token
        return JwtUtils.generateToken(u.getId(), u.getUsername(), u.getRole());
    }

    private UserVO toVO(User u) {
        if (u == null) return null;
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(u, vo);
        return vo;
    }
}