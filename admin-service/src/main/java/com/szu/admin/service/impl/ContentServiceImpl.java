package com.szu.admin.service.impl;

import com.szu.admin.common.PageResult;
import com.szu.admin.domain.Content;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateContentDTO;
import com.szu.admin.dto.query.ContentQueryDTO;
import com.szu.admin.mapper.ContentMapper;
import com.szu.admin.service.ContentService;
import com.szu.admin.vo.ContentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public PageResult<ContentVO> listContents(ContentQueryDTO dto) {
        List<Content> list = contentMapper.listContents(dto.getIsDeleted(), dto.getStatus(), dto.getTagIds());
        long total = contentMapper.countContents(dto.getIsDeleted(), dto.getStatus(), dto.getTagIds());
        List<ContentVO> vos = list.stream().skip((long)(dto.getPage()-1)*dto.getSize()).limit(dto.getSize()).map(this::toVO).collect(Collectors.toList());
        return new PageResult<>(total, vos);
    }

    @Override
    public void changeStatus(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        Content c = new Content();
        c.setId(dto.getId());
        c.setStatus(dto.getValue());
        contentMapper.update(c);
    }

    @Override
    public void changeDeleted(@RequestBody ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getValue() == null) throw new RuntimeException("参数不完整");
        Content c = new Content();
        c.setId(dto.getId());
        c.setIsDeleted(dto.getValue());
        contentMapper.update(c);
    }

    @Override
    public ContentVO detail(Long id) {
        Content c = contentMapper.findById(id);
        if (c == null) return null;
        return toVO(c);
    }

    @Override
    public void updateContent(UpdateContentDTO dto) {
        if (dto.getId() == null) throw new RuntimeException("id 不能为空");
        Content c = new Content();
        c.setId(dto.getId());
        c.setTitle(dto.getTitle());
        c.setCoverImage(dto.getCoverImage());
        c.setContentBody(dto.getContentBody());
        c.setStatus(dto.getStatus());
        c.setIsDeleted(dto.getIsDeleted());
        contentMapper.update(c);
    }

    private ContentVO toVO(Content c) {
        ContentVO vo = new ContentVO();
        BeanUtils.copyProperties(c, vo);
        return vo;
    }
}
