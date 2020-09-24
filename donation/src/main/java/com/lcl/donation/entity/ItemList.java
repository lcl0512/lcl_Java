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
public class ItemList implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private int id;
    /**
     * 物品清单外键id
     */
    private Integer donationInfoId;

    /**
     * 币种
     */
    private String currency;

    /**
     * 捐献金额
     */
    private Float amount;

    /**
     * 捐赠物名称
     */
    private String itemName;

    /**
     * 捐赠数量
     */
    private String itemAmount;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 生产标准
     */
    private String standard;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
