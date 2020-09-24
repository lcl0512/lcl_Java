package com.lcl.donation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.donation.entity.Notice;
import com.lcl.donation.mapper.NoticeMapper;
import com.lcl.donation.service.INoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.donation.service.vo.request.RequestNoticeListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {
    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public IPage<Notice> querryPage(RequestNoticeListVo requestNoticeListVo) {
        QueryWrapper<Notice> queryWrapper = new QueryWrapper<>();
        //条件构造，当 username，telephone，email不为空时进行模糊查询
        queryWrapper.like(!ObjectUtils.isEmpty(requestNoticeListVo.getTitle()),"title",requestNoticeListVo.getTitle());
        queryWrapper.like(!ObjectUtils.isEmpty(requestNoticeListVo.getNoticeText()),"notice_text",requestNoticeListVo.getNoticeText());

        IPage<Notice> page = new Page<>(requestNoticeListVo.getDisplayStart(),requestNoticeListVo.getDisplayLength());
        IPage<Notice> userPage = noticeMapper.selectPage(page, queryWrapper);
        return userPage;
    }
}
