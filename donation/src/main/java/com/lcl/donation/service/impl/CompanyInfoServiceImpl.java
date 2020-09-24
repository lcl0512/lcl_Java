package com.lcl.donation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.donation.entity.CompanyInfo;
import com.lcl.donation.mapper.CompanyInfoMapper;
import com.lcl.donation.service.ICompanyInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.donation.service.vo.request.RequestCompanyInfoListVo;
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
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements ICompanyInfoService {
    @Autowired
    CompanyInfoMapper companyInfoMapper;

    @Override
    public List<CompanyInfo> querryAll() {
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","company_name");
        List<CompanyInfo> companyInfos = companyInfoMapper.selectList(queryWrapper);
        return companyInfos;
    }

    @Override
    public List<String> getCompanyNameByIds(List<Integer> ids) {
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
        List<String> companyNames = new ArrayList<>();
        for(Integer id : ids){
            queryWrapper.select("company_name").eq("id",id);
            CompanyInfo companyInfo = companyInfoMapper.selectOne(queryWrapper);
            companyNames.add(companyInfo.getCompanyName());
        }
        return companyNames;
    }


    @Override
    public IPage<CompanyInfo> querryPage(RequestCompanyInfoListVo companyInfoListVo) {
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
        //条件构造，当 companyName，companyAddress，CompanyLinkman不为空时进行模糊查询
        queryWrapper.like(!ObjectUtils.isEmpty(companyInfoListVo.getCompanyName()),"company_name",companyInfoListVo.getCompanyName());
        queryWrapper.like(!ObjectUtils.isEmpty(companyInfoListVo.getCompanyAddress()),"company_address",companyInfoListVo.getCompanyAddress());
        queryWrapper.like(!ObjectUtils.isEmpty(companyInfoListVo.getCompanyLinkman()),"company_linkman",companyInfoListVo.getCompanyLinkman());
        System.out.println(companyInfoListVo.getCompanyName());
        Page<CompanyInfo> page = new Page<>(companyInfoListVo.getDisplayStart(),companyInfoListVo.getDisplayLength());
        IPage<CompanyInfo> companyInfoPage = companyInfoMapper.selectPage(page, queryWrapper);
        return companyInfoPage;
    }
}
