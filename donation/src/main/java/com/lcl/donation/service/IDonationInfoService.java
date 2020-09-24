package com.lcl.donation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.entity.DonationInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.donation.entity.ItemList;
import com.lcl.donation.service.vo.request.RequestDonateVo;
import com.lcl.donation.service.vo.request.DonationInfoVo;
import com.lcl.donation.service.vo.response.RespDonationInfoVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
public interface IDonationInfoService extends IService<DonationInfo> {
    public Boolean addDonateInfo(List<ItemList> itemLists, int donationInfoId);
    public IPage<RespDonationInfoVo> queryPage(DonationInfoVo donationInfoListVo);
    /**
     * 删除donation_info表中记录的同时删除item_list表中相关记录
     */
    public boolean removeDonation(int id);

}
