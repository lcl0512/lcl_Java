package com.lcl.donation.service.vo.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class DonationProjectVo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 捐献单位外键
     */
    private Integer companyId;
    private String companyName;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 项目详情
     */
    private String projectDesc;

    /**
     * 项目负责人
     */
    private String projectLeader;

    /**
     * 项目状态(0是筹款中，1是结束筹款）
     */
    private int projectStatus;

    private LocalDateTime createTime;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;
}
