package com.lcl.donation.service.vo.request;

import com.lcl.donation.common.Search;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestNoticeVo{
    private int id;

    private String title;

    private String noticeText;

    private LocalDateTime createTime;

}
