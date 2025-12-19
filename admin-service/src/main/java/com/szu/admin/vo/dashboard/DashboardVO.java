package com.szu.admin.vo.dashboard;

import lombok.Data;

@Data
public class DashboardVO {
    // 注册用户总数
    private Long totalUsers;
    // 累计发布内容数
    private Long totalContents;
    // 当日新增内容数
    private Long todayContents;
    // 当日新增用户数
    private Long todayUsers;
}
