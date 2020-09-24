package com.lcl.donation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.donation.service.vo.request.RequestNoticeListVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
public interface INoticeService extends IService<Notice> {

    IPage<Notice> querryPage(RequestNoticeListVo requestNoticeListVo);
}
