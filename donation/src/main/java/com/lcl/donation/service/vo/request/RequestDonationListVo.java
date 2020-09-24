package com.lcl.donation.service.vo.request;

import com.lcl.donation.common.Search;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestDonationListVo  extends Search {
    private Integer userId;

    private Integer projectId;

    private String projectName;

    private String projectDesc;

    private Integer type;

    private String donor;

    private String status;

    private LocalDateTime createTime;
}
