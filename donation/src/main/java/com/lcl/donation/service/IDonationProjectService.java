package com.lcl.donation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.entity.DonationProject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.donation.service.vo.request.ProjectListVo;
import com.lcl.donation.service.vo.response.DonationProjectVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */

public interface IDonationProjectService extends IService<DonationProject> {
    public List<DonationProject> getByCompanyId(int companyId);
    public IPage<DonationProjectVo> queryPage(ProjectListVo projectListVo);
    public List<DonationProject> getProjectByStatus(int status);
}
