package com.lcl.donation.service.vo.request;


import lombok.Data;
import lombok.Getter;

@Getter
public class RequestLoginVo {
    private String telephone;
    private String password;
    private String captcha;
}
