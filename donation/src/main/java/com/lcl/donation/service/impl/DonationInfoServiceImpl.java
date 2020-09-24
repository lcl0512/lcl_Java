package com.lcl.donation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.donation.entity.DonationInfo;
import com.lcl.donation.entity.ItemList;
import com.lcl.donation.mapper.DonationInfoMapper;
import com.lcl.donation.service.IDonationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.donation.service.IItemListService;
import com.lcl.donation.service.vo.request.DonationInfoVo;
import com.lcl.donation.service.vo.response.RespDonationInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class DonationInfoServiceImpl extends ServiceImpl<DonationInfoMapper, DonationInfo> implements IDonationInfoService {
    @Autowired
    DonationInfoMapper donationInfoMapper;
    @Autowired
    IItemListService itemListService;
    @Override
    public Boolean addDonateInfo(List<ItemList> itemLists,int donationInfoId) {
        for(ItemList itemList : itemLists){
            itemList.setDonationInfoId(donationInfoId);
        }
        System.out.println(itemLists);
        if(itemListService.saveBatch(itemLists)){
            return true;
        }
        return false;
    }

    @Override
    public IPage<RespDonationInfoVo> queryPage(DonationInfoVo donationInfoVo) {
        List<RespDonationInfoVo> respDonationInfo = donationInfoMapper.getDonationInfo(donationInfoVo);
        for(RespDonationInfoVo donationInfo : respDonationInfo){
            donationInfo.setTime(donationInfo.getCreateTime().plusDays(3));
            donationInfo.setDonateNum(donationInfo.getId().toString());
        }
        System.out.println(respDonationInfo);
        System.out.println(donationInfoVo);

        IPage<RespDonationInfoVo> donationInfoVoIPage = new Page<>(donationInfoVo.getDisplayStart(),donationInfoVo.getDisplayLength());
        donationInfoVoIPage.setRecords(respDonationInfo);
        return donationInfoVoIPage;
    }

    @Override
    public boolean removeDonation(int id) {
        int i = donationInfoMapper.deleteById(id);
        boolean b = itemListService.removeByDonationInfoId(id);
        if(i!=0 && b){
            return true;
        }
        return false;
    }
}
