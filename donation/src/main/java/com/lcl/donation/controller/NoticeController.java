package com.lcl.donation.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.common.Result;
import com.lcl.donation.entity.Notice;
import com.lcl.donation.service.INoticeService;
import com.lcl.donation.service.vo.request.RequestDeleteNoticesVo;
import com.lcl.donation.service.vo.request.RequestNoticeListVo;
import com.lcl.donation.service.vo.request.RequestNoticeVo;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    INoticeService noticeService;
    @PostMapping("/list")
    public Result getUserList(@RequestBody RequestNoticeListVo noticeListVo){
        IPage<Notice> noticeIPage = noticeService.querryPage(noticeListVo);
//        System.out.println(userIPage.getRecords().get(0).getCreatTime());
        return Result.getSuccess().setData(noticeIPage);
    }
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdateNotice(@RequestBody RequestNoticeVo requestNoticeVo){
        Notice notice = new Notice();
        BeanUtils.copyProperties(requestNoticeVo,notice);
        notice.setCreateTime(LocalDateTime.now());
        if(notice.getId()!=0){
            if(noticeService.saveOrUpdate(notice)){
                return Result.getSuccess().setMsg("修改成功");
            }
        }else if(noticeService.saveOrUpdate(notice)){
            return Result.getSuccess().setMsg("添加成功");
        }
        return Result.getFailure().setMsg("添加失败");
    }
    @DeleteMapping("/deleteByIds")
    public Result deleteNotices(@RequestBody RequestDeleteNoticesVo noticesVo){
        List<Integer> ids = noticesVo.getIds();
        if (noticeService.removeByIds(ids)){
            return Result.getSuccess().setMsg("删除成功");
        }
        return Result.getSuccess().setMsg("删除失败");
    }
}
