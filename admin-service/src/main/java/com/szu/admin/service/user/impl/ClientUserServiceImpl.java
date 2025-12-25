package com.szu.admin.service.user.impl;

import com.szu.admin.common.PageResult;
import com.szu.admin.domain.ClientUser;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateClientUserDTO;
import com.szu.admin.dto.query.UserQueryDTO;
import com.szu.admin.mapper.user.ClientUserMapper;
import com.szu.admin.service.user.ClientUserService;
import com.szu.admin.utils.StringUtils;
import com.szu.admin.vo.user.ClientUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientUserServiceImpl implements ClientUserService {

    @Autowired
    private ClientUserMapper clientUserMapper;

    @Override
    public PageResult<ClientUserVO> listClientUsers(UserQueryDTO dto) {

        // 排序方式
        String sortBy = dto.getSortBy();
        String sortOrder = dto.getSortOrder();

        // 排序方式校验
        Set<String> allowSortFields = Set.of(
                "create_time", "update_time"
        );
        if (!allowSortFields.contains(sortBy)) {
            sortBy = "create_time";
        }
        if (!"asc".equalsIgnoreCase(sortOrder) && !"desc".equalsIgnoreCase(sortOrder)) {
            sortOrder = "desc";
        }

        List<ClientUser> list = clientUserMapper.listClientUsers(dto.getIsDeleted(), dto.getStatus(), dto.getName(), sortBy, sortOrder);
        long total = clientUserMapper.countClientUsers(dto.getIsDeleted(), dto.getStatus(), dto.getName());
        List<ClientUserVO> vos = list.stream().skip((long)(dto.getPage()-1)*dto.getSize()).limit(dto.getSize()).map(this::toVO).collect(Collectors.toList());
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

        if (dto.getSex().equals("男") && dto.getSex().equals("女")) {
            throw new RuntimeException("请输入正确的性别");
        }

        ClientUser u = new ClientUser();
        u.setId(dto.getId());
        u.setName(StringUtils.blankToNull(dto.getName()));
        u.setAvatar(StringUtils.blankToNull(dto.getAvatar()));
        u.setSex(StringUtils.blankToNull(dto.getSex()));
        u.setArea(StringUtils.blankToNull(dto.getArea()));
        u.setSignature(StringUtils.blankToNull(dto.getSignature()));

        clientUserMapper.updateClientUser(u);
    }

    @Override
    public void changeClientUserStatus(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getStatus() == null) throw new RuntimeException("参数不完整");
        if (dto.getStatus() == 0 && dto.getBanReason() == null) throw new RuntimeException("请输入封禁原因");
        ClientUser u = new ClientUser();
        u.setId(dto.getId());
        u.setStatus(dto.getStatus());
        u.setBanReason(dto.getBanReason());
        clientUserMapper.updateClientUser(u);
    }

    @Override
    public void changeClientUserDeleted(ChangeDeletedDTO dto) {
        if (dto.getId() == null || dto.getIsDeleted() == null) throw new RuntimeException("参数不完整");
        ClientUser u = new ClientUser();
        u.setId(dto.getId());
        u.setIsDeleted(dto.getIsDeleted());
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
