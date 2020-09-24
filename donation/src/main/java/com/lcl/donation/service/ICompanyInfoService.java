package com.lcl.donation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.entity.CompanyInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.donation.service.vo.request.RequestCompanyInfoListVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
public interface ICompanyInfoService extends IService<CompanyInfo> {

    /**
     *
     * @return 公司信息列表
     */
    public List<CompanyInfo> querryAll();
    public List<String> getCompanyNameByIds(List<Integer> ids);
    public IPage<CompanyInfo> querryPage(RequestCompanyInfoListVo companyInfoListVo);
}
