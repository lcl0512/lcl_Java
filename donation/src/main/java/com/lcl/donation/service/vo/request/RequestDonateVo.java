package com.lcl.donation.service.vo.request;

import com.lcl.donation.entity.ItemList;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class RequestDonateVo {
    private String donor;
    private List<ItemList> itemListVos;
    private Integer organize; //单位Id
    private Integer projectId;
    private String status;
    private Integer type;
    private Integer userId;
}
