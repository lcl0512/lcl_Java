package com.lcl.donation.service.vo.request;

import com.lcl.donation.common.Search;
import lombok.Getter;

@Getter
public class RequestUserListVo extends Search {
    public String username;
    public String  email;
    public String telephone;
}
