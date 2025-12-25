package com.szu.admin.service.content.impl;

import com.szu.admin.common.PageResult;
import com.szu.admin.domain.Content;
import com.szu.admin.dto.ChangeDeletedDTO;
import com.szu.admin.dto.ChangeStatusDTO;
import com.szu.admin.dto.UpdateContentDTO;
import com.szu.admin.dto.query.ContentQueryDTO;
import com.szu.admin.mapper.content.ContentMapper;
import com.szu.admin.service.content.ContentService;
import com.szu.admin.vo.content.ContentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public PageResult<ContentVO> listContents(ContentQueryDTO dto) {

        // 排序方式
        String sortBy = dto.getSortBy();
        String sortOrder = dto.getSortOrder();

        // 排序方式校验
        Set<String> allowSortFields = Set.of(
                "created_at", "updated_at", "view_count"
        );
        if (!allowSortFields.contains(sortBy)) {
            sortBy = "created_at";
        }
        if (!"asc".equalsIgnoreCase(sortOrder) && !"desc".equalsIgnoreCase(sortOrder)) {
            sortOrder = "desc";
        }

        List<Content> list = contentMapper.listContents(dto.getIsDeleted(), dto.getStatus(), dto.getTagIds(), dto.getKeyword(), sortBy, sortOrder);
        long total = contentMapper.countContents(dto.getIsDeleted(), dto.getStatus(), dto.getTagIds(), dto.getKeyword());
        List<ContentVO> vos = list.stream().skip((long)(dto.getPage()-1)*dto.getSize()).limit(dto.getSize()).map(this::toVO).collect(Collectors.toList());
        return new PageResult<>(total, vos);
    }

    @Override
    public void changeStatus(ChangeStatusDTO dto) {
        if (dto.getId() == null || dto.getStatus() == null) throw new RuntimeException("参数不完整");
        if (dto.getStatus() == 0 && dto.getBanReason() == null) throw new RuntimeException("请输入封禁原因");
        Content c = new Content();
        c.setId(dto.getId());
        c.setStatus(dto.getStatus());
        c.setBanReason(dto.getBanReason());
        contentMapper.update(c);
    }

    @Override
    public void changeDeleted(@RequestBody ChangeDeletedDTO dto) {
        if (dto.getId() == null || dto.getIsDeleted() == null) throw new RuntimeException("参数不完整");
        Content c = new Content();
        c.setId(dto.getId());
        c.setIsDeleted(dto.getIsDeleted());
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
        c.setViewCount(dto.getViewCount());
        // 下面的也许要校验格式？
        c.setCommentCount(dto.getCommentCount());
        c.setComments(dto.getComments());
        c.setTags(dto.getTags());
        c.setLikes(dto.getLikes());
        c.setImages(dto.getImages());
        contentMapper.update(c);
    }

    private ContentVO toVO(Content c) {
        ContentVO vo = new ContentVO();
        BeanUtils.copyProperties(c, vo);
        return vo;
    }
}
