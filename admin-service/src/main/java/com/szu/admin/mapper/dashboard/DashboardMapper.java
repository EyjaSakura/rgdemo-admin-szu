package com.szu.admin.mapper.dashboard;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    // 统计平台用户总数
    Long countUsers();
    // 统计平台内容总数
    Long countContents();
    // 统计今日新增内容数量
    Long countTodayContents();
    // 统计今日平台新增点赞数量（暂未使用）
    Long countTodayLikes();
    // 统计今日平台新增评分数量（暂未使用）
    Long countTodayRatings();
    // 统计今日平台新增用户数量
    Long countTodayUsers();
}
