package com.szu.admin.controller.dashboard;

import com.szu.admin.common.Result;
import com.szu.admin.service.dashboard.DashboardService;
import com.szu.admin.vo.dashboard.DashboardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/summary")
    public Result<DashboardVO> summary() {
        return Result.ok(dashboardService.getSummary());
    }
}
