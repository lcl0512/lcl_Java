package com.lcl.donation.common;

import com.lcl.donation.entity.DonationProject;
import com.lcl.donation.service.IDonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 *@description: 项目定时任务，@Component：组件，@EnableScheduling：开启
定时任务，@EnableAsync：开启异步执行（多线程），@Scheduled(fixedDelay =
30000)30 秒执行一次
 *@author: zhushubiao
 *@email: 617374631@qq.com
 *@date: 2020/2/18
 *@time: 15:39
 */
@Component
@EnableScheduling
@EnableAsync
public class ProjectScheduleTask {
    @Autowired
    private IDonationProjectService projectService;
    @Async
    @Scheduled(fixedDelay = 30000)
    public void updateProjectStatus() {
        List<DonationProject> projectList = projectService.getProjectByStatus(0);

        LocalDateTime localTime = LocalDateTime.now();
        for (DonationProject project : projectList){
            //如果结束时间在此刻时间之前，则更新状态为结束筹集
            if (project.getEndTime().isBefore(localTime)){
                project.setProjectStatus(1);
            }
        }
        System.out.println("定时任务执行中");
        projectService.saveOrUpdateBatch(projectList);
    }
}
