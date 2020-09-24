package com.lcl.donation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcl.donation.entity.ItemList;
import com.lcl.donation.mapper.ItemListMapper;
import com.lcl.donation.service.IItemListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@Service
public class ItemListServiceImpl extends ServiceImpl<ItemListMapper, ItemList> implements IItemListService {
    @Autowired
    ItemListMapper itemListMapper;
    @Override
    public boolean removeByDonationInfoId(int DonationInfoId) {
        QueryWrapper<ItemList> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("donation_info_id",DonationInfoId);
        if(itemListMapper.delete(queryWrapper)!=0){
            return true;
        }
        return false;
    }
}
