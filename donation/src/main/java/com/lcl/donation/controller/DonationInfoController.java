package com.lcl.donation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.common.Result;
import com.lcl.donation.entity.DonationInfo;
import com.lcl.donation.entity.DonationProject;
import com.lcl.donation.service.IDonationInfoService;
import com.lcl.donation.service.IDonationProjectService;
import com.lcl.donation.service.vo.request.ProjectListVo;
import com.lcl.donation.service.vo.request.RequestCheckDonation;
import com.lcl.donation.service.vo.request.RequestDonateVo;
import com.lcl.donation.service.vo.request.DonationInfoVo;
import com.lcl.donation.service.vo.response.DonationProjectVo;
import com.lcl.donation.service.vo.response.RespDonationInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@RestController
@RequestMapping("/donation-info")
public class DonationInfoController {
    @Autowired
    IDonationProjectService donationProjectService;
    @Autowired
    IDonationInfoService donationInfoService;

    @PostMapping("/list")
    public Result getDonationProjectList(@RequestBody ProjectListVo projectListVo) {
        IPage<DonationProjectVo> donationProjectIPage = donationProjectService.queryPage(projectListVo);
        return Result.getSuccess().setData(donationProjectIPage);
    }
    @PostMapping("/donateInfoList")
    public Result getDonationInfoList(@RequestBody DonationInfoVo donationInfoListVo){
        System.out.println(donationInfoListVo);
        IPage<RespDonationInfoVo> respDonationInfoVoIPage = donationInfoService.queryPage(donationInfoListVo);
        return Result.getSuccess().setData(respDonationInfoVoIPage);
    }

    @PostMapping("/donate")
    public Result donate(@RequestBody RequestDonateVo requestDonateVo) {
        DonationInfo donationInfo = new DonationInfo();
        BeanUtils.copyProperties(requestDonateVo,donationInfo);
        //从捐献项目表（DonationProject）中获取到项目名、项目详情，插入到捐献详情表（DonationInfo）中
        DonationProject donationProject = donationProjectService.getById(requestDonateVo.getProjectId());
        donationInfo.setCreateTime(LocalDateTime.now());
        donationInfo.setProjectName(donationProject.getProjectName());
        donationInfo.setProjectDesc(donationProject.getProjectDesc());

        if(donationInfoService.save(donationInfo)){
            if(donationInfoService.addDonateInfo(requestDonateVo.getItemListVos(),donationInfo.getId())){
                return Result.getSuccess().setMsg("捐献成功");
            }

        }
        return Result.getFailure().setMsg("捐献失败");
    }
    @PostMapping("/checkDonation")
    public Result checkDonation(@RequestBody RequestCheckDonation resVo){
        if(resVo.getCheckStatus()){
            DonationInfo donationInfo = donationInfoService.getById(resVo.getId());
            donationInfo.setStatus(1);
            donationInfoService.saveOrUpdate(donationInfo);
            return Result.getSuccess().setMsg("批准捐献");
        }else {
            donationInfoService.removeDonation(resVo.getId());
            return Result.getSuccess().setMsg("不批准");
        }
    }
}
