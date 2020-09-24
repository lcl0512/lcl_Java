package com.lcl.donation.service.vo.request;

import com.lcl.donation.common.Search;
import com.lcl.donation.entity.ItemList;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DonationInfoVo extends Search {
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

    @Override
    public String toString() {
        return "DonationInfoVo{" +
                "projectName='" + projectName + '\'' +
                ", projectStatus=" + projectStatus +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", type=" + type +
                ", responseItemListVoList=" + responseItemListVoList +
                ", id=" + id +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", projectDesc='" + projectDesc + '\'' +
                ", donor='" + donor + '\'' +
                '}';
    }
}
