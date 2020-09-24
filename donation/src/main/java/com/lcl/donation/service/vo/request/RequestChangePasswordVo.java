package com.lcl.donation.service.vo.request;

import lombok.Getter;

@Getter
public class RequestChangePasswordVo {
    private int id;
    private String newPassword;
    private String password;
}
