package com.lcl.donation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.common.Result;
import com.lcl.donation.service.vo.request.RequestDeleteProjectVo;
import com.lcl.donation.service.vo.response.DonationProjectVo;
import com.lcl.donation.utils.TimeTransfer;
import com.lcl.donation.entity.DonationProject;
import com.lcl.donation.service.IDonationProjectService;
import com.lcl.donation.service.vo.request.ProjectListVo;
import com.lcl.donation.service.vo.request.RequestAddDonationProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/donation-project")
public class DonationProjectController {
    @Autowired
    IDonationProjectService donationProjectService;
    @RequestMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RequestAddDonationProjectVo donationProjectVo){
        DonationProject donationProject = new DonationProject();
        donationProject.setProjectName(donationProjectVo.getProjectName());
        donationProject.setProjectDesc(donationProjectVo.getProjectDesc());
//        donationProject.setProjectStatus(donationProjectVo.getProjectStatus());
        donationProject.setProjectLeader(donationProjectVo.getProjectLeader());
        donationProject.setStartTime(TimeTransfer.getDateTimeOfTimestamp(donationProjectVo.getStartTime()));
        donationProject.setEndTime(TimeTransfer.getDateTimeOfTimestamp(donationProjectVo.getEndTime()));
        donationProject.setCompanyId(donationProjectVo.getCompanyId());
        donationProject.setId(donationProjectVo.getId());
        if(donationProjectService.saveOrUpdate(donationProject)){
            if(donationProject.getId()!=0){
                return Result.getSuccess().setMsg("修改捐献项目成功");
            }
            return Result.getSuccess().setMsg("添加捐献项目成功");
        }else{
            return Result.getFailure().setMsg("添加捐献项目失败");
        }
    }
    @PostMapping("/list")
    public Result getProjectList(@RequestBody ProjectListVo projectListVo){
        IPage<DonationProjectVo> donationProjectIPage = donationProjectService.queryPage(projectListVo);
        return Result.getSuccess().setData(donationProjectIPage);
    }
    @DeleteMapping("/deleteByIds")
    public Result deleteProject(@RequestBody RequestDeleteProjectVo projectVo){
        List<Integer> ids = projectVo.getIds();
        if (donationProjectService.removeByIds(ids)){
            return Result.getSuccess().setMsg("删除成功");
        }
        return Result.getSuccess().setMsg("删除失败");
    }

    @RequestMapping("/queryByCompanyId/{companyId}")
    public Result queryByCompanyId(@PathVariable int companyId){
        List<DonationProject> projects = donationProjectService.getByCompanyId(companyId);
        return Result.getSuccess().setData(projects);
    }

}
