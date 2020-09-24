package com.lcl.donation.service.vo.request;

import com.lcl.donation.common.Search;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestNoticeListVo extends Search {
    private String title;
    private String noticeText;
}
