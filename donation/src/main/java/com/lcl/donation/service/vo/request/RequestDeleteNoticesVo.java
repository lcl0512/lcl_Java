package com.lcl.donation.service.vo.request;

import com.lcl.donation.entity.Notice;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestDeleteNoticesVo {
    List<Integer> ids;
}
