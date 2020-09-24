package com.lcl.donation.service.vo.request;

import com.lcl.donation.entity.DonationProject;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestDeleteProjectVo {
    List<Integer> ids;
}
