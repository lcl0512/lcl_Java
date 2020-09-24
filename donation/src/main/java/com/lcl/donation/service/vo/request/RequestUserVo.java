package com.lcl.donation.service.vo.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RequestUserVo {
    private int id;

    private String username;

    private String password;

    private String telephone;

    private String sex;

    private Integer age;

    private String province;

    private String city;

    private String local;

    private String email;
}
