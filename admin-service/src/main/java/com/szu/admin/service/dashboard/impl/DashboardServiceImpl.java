package com.szu.admin.service.dashboard.impl;

import com.szu.admin.mapper.dashboard.DashboardMapper;
import com.szu.admin.service.dashboard.DashboardService;
import com.szu.admin.vo.dashboard.DashboardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardMapper dashboardMapper;

    @Override
    public DashboardVO getSummary() {
        DashboardVO vo = new DashboardVO();
        vo.setTotalUsers(dashboardMapper.countUsers());
        vo.setTotalContents(dashboardMapper.countContents());
        vo.setTodayContents(dashboardMapper.countTodayContents());
        vo.setTodayUsers(dashboardMapper.countTodayUsers());
        return vo;
    }
}
