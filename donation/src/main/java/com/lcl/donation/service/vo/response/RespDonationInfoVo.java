package com.lcl.donation.service.vo.response;

import com.lcl.donation.entity.ItemList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@ToString
public class RespDonationInfoVo implements Serializable {
    private String projectName;
    private Integer projectStatus;
    private LocalDateTime createTime;
    private String status;
    private String type;
    private List<ItemList> responseItemListVoList;
    private Integer id;
    private Integer userId;
    private Integer projectId;
    private String projectDesc;
    private String donor;
    private LocalDateTime time;
    private String donateNum;
    private String companyName;
}
