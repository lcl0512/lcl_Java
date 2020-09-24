package com.lcl.donation.service.vo.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RequestAddCompanyVo {
    private int id;
    private String companyName;
    private String companyAddress;

    private String companyLinkman;

    private String companyPhone;

}
