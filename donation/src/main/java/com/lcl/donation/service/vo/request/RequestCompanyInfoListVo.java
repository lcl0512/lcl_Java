package com.lcl.donation.service.vo.request;

import lombok.Getter;

@Getter
public class RequestCompanyInfoListVo {
    private String companyName;

    private String companyAddress;

    private String companyLinkman;

    private int displayStart;

    private int displayLength;


}
