package com.lcl.donation.controller;


import com.lcl.donation.common.Result;
import com.lcl.donation.entity.ItemList;
import com.lcl.donation.service.IItemListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/item-list")
public class ItemListController {
    @Autowired
    IItemListService itemListService;
    public Result saveOrUpdate(@RequestBody ItemList itemList){
        itemList.setCreateTime(LocalDateTime.now());
        if(itemList.getId()!=0){
            if(itemListService.saveOrUpdate(itemList)){
                Result.getSuccess().setMsg("修改成功");
            }
        }else{
            if(itemListService.saveOrUpdate(itemList)){
                Result.getSuccess().setMsg("添加成功");
            }
        }
        return Result.getFailure();
    }


}
