package com.lcl.donation.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DonationProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 捐献单位外键
     */
    private Integer companyId;

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
