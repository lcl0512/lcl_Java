package com.lcl.donation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.donation.entity.CompanyInfo;
import com.lcl.donation.entity.DonationProject;
import com.lcl.donation.mapper.DonationProjectMapper;
import com.lcl.donation.service.ICompanyInfoService;
import com.lcl.donation.service.IDonationProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.donation.service.vo.request.ProjectListVo;
import com.lcl.donation.service.vo.response.DonationProjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@Service
public class DonationProjectServiceImpl extends ServiceImpl<DonationProjectMapper, DonationProject> implements IDonationProjectService {

    @Autowired
    DonationProjectMapper donationProjectMapper;

    @Override
    public List<DonationProject> getByCompanyId(int companyId) {
        QueryWrapper<DonationProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id",companyId);
        List<DonationProject> donationProjects = donationProjectMapper.selectList(queryWrapper);
        return donationProjects;
    }

    @Autowired
    ICompanyInfoService companyInfoService;
    @Override
    public IPage<DonationProjectVo> queryPage(ProjectListVo projectListVo) {
        QueryWrapper<DonationProject> queryWrapper = new QueryWrapper<>();
        //条件构造，当 companyName，companyAddress，CompanyLinkman不为空时进行模糊查询
        queryWrapper.like(!ObjectUtils.isEmpty(projectListVo.getProjectName()),"project_name",projectListVo.getProjectName());
        queryWrapper.like(!ObjectUtils.isEmpty(projectListVo.getProjectStatus()),"project_status",projectListVo.getProjectStatus());
        Page<DonationProject> page = new Page<>(projectListVo.getDisplayStart(),projectListVo.getDisplayLength());
        Page<DonationProject> donationProjectPage = donationProjectMapper.selectPage(page, queryWrapper);
        Page<DonationProjectVo> page1 = new Page<>(projectListVo.getDisplayStart(),projectListVo.getDisplayLength());
        List<DonationProjectVo> donationProjectVos = new ArrayList<>();
        //page1.convert();
        for(DonationProject record : donationProjectPage.getRecords()){
            DonationProjectVo projectVo = new DonationProjectVo();
            BeanUtils.copyProperties(record,projectVo);
            String  company = companyInfoService.getById(record.getCompanyId()).getCompanyName();
            projectVo.setCompanyName(company);
            donationProjectVos.add(projectVo);
        }
        page1.setRecords(donationProjectVos);

        return page1;
    }

    @Override
    public List<DonationProject> getProjectByStatus(int status) {
        QueryWrapper<DonationProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_status",0);
        List<DonationProject> donationProjects = donationProjectMapper.selectList(queryWrapper);
        return donationProjects;
    }

}
