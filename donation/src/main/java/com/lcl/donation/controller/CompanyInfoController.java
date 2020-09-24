package com.lcl.donation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.common.Result;
import com.lcl.donation.entity.CompanyInfo;
import com.lcl.donation.service.ICompanyInfoService;
import com.lcl.donation.service.vo.request.RequestAddCompanyVo;
import com.lcl.donation.service.vo.request.RequestCompanyInfoListVo;
import com.lcl.donation.service.vo.request.RequestDeleteCompaniesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/company-info")
public class CompanyInfoController {
    @Autowired
    ICompanyInfoService companyInfoService;
    @PostMapping("/list")
    public Result getCompanyList(@RequestBody RequestCompanyInfoListVo companyInfoListVo){
        IPage<CompanyInfo> companyInfoIPage = companyInfoService.querryPage(companyInfoListVo);
        return Result.getSuccess().setData(companyInfoIPage);
    }

    @DeleteMapping("deleteByIds")
    public Result deleteCompanies(@RequestBody RequestDeleteCompaniesVo requestDeleteCompaniesVo){
        List<Integer> ids = requestDeleteCompaniesVo.getIds();
        boolean isDelete = companyInfoService.removeByIds(ids);
        if(isDelete){
            return Result.getSuccess().setMsg("删除成功");
        }
        return Result.getFailure().setMsg("删除失败");
    }

    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdateCompanyInfo(@RequestBody RequestAddCompanyVo requestAddCompanyVo){
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyName(requestAddCompanyVo.getCompanyName());
        companyInfo.setCompanyAddress(requestAddCompanyVo.getCompanyAddress());
        companyInfo.setCompanyLinkman(requestAddCompanyVo.getCompanyLinkman());
        companyInfo.setCompanyPhone(requestAddCompanyVo.getCompanyPhone());
        companyInfo.setCreateTime(LocalDateTime.now());
        if(requestAddCompanyVo.getId()!=0){
            companyInfo.setId(requestAddCompanyVo.getId());
            if(companyInfoService.saveOrUpdate(companyInfo)){
                return Result.getSuccess().setMsg("修改成功");
            }
        }else if(companyInfoService.saveOrUpdate(companyInfo)){
            return Result.getSuccess().setMsg("添加成功");
        }
        return Result.getFailure().setMsg("添加失败");
    }
    @RequestMapping("/getAllCompanyName")
    public Result getAllCompany(){
        List<CompanyInfo> companies = companyInfoService.querryAll();
        System.out.println(companies);
        if(companies!=null){
            return Result.getSuccess().setData(companies);
        }

        return Result.getFailure();
    }

}
