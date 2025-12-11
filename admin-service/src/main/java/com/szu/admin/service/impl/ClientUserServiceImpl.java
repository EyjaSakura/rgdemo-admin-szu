package com.szu.admin.service.impl;

import com.szu.admin.common.PageResult;
import com.szu.admin.domain.ClientUser;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateClientUserDTO;
import com.szu.admin.mapper.ClientUserMapper;
import com.szu.admin.service.ClientUserService;
import com.szu.admin.utils.StringUtils;
import com.szu.admin.vo.ClientUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientUserServiceImpl implements ClientUserService {

    @Autowired
    private ClientUserMapper clientUserMapper;

    @Override
    public PageResult<ClientUserVO> listClientUsers(Integer status, Integer isDeleted, int page, int size) {
        List<ClientUser> list = clientUserMapper.listClientUsers(status, isDeleted);
        long total = clientUserMapper.countClientUsers(status, isDeleted);
        List<ClientUserVO> vos = list.stream().skip((long)(page-1)*size).limit(size).map(this::toVO).collect(Collectors.toList());
        return new PageResult<>(total, vos);
    }

    @Override
    public void updateClientUser(UpdateClientUserDTO dto) {
        if (dto.getId() == null) throw new RuntimeException("id 不能为空");
        ClientUser old = clientUserMapper.findClientUserById(dto.getId());
        if (old == null) throw new RuntimeException("用户不存在");

        if (dto.getName() != null && !dto.getName().matches("[A-Za-z0-9]*")) {
            throw new RuntimeException("昵称只能是英文字母或数字");
        }

        ClientUser u = new ClientUser();
        u.setId(dto.getId());
        u.setName(StringUtils.blankToNull(dto.getName()));
        u.setAvatar(StringUtils.blankToNull(dto.getAvatar()));

        clientUserMapper.updateClientUser(u);
    }

    @Override
    public void changeClientUserStatus(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        ClientUser u = new ClientUser();
        u.setId(dto.getId());
        u.setStatus(dto.getValue());
        clientUserMapper.updateClientUser(u);
    }

    @Override
    public void changeClientUserIsDeleted(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        ClientUser u = new ClientUser();
        u.setId(dto.getId());
        u.setIsDeleted(dto.getValue());
        clientUserMapper.updateClientUser(u);
    }

    @Override
    public ClientUserVO detailClientUser(Long id) {
        ClientUser u = clientUserMapper.findClientUserById(id);
        return toVO(u);
    }

    private ClientUserVO toVO(ClientUser u) {
        if (u == null) return null;
        ClientUserVO vo = new ClientUserVO();
        BeanUtils.copyProperties(u, vo);
        return vo;
    }
}
