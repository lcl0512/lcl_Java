package com.lcl.donation.service.vo.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestAddDonationProjectVo {

    private int id;
    private String projectName;
    private String projectDesc;
    private Integer projectStatus;
    private int companyId;
    private String projectLeader;
    private Long endTime;
    private Long startTime;

}
