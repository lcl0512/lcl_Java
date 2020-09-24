package com.lcl.donation.service;

import com.lcl.donation.entity.ItemList;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
public interface IItemListService extends IService<ItemList> {
    public boolean removeByDonationInfoId(int DonationInfoId);

}
