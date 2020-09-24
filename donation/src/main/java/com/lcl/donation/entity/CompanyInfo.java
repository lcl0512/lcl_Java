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
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String companyName;

    private String companyAddress;

    private String companyLinkman;

    private String companyPhone;

    private LocalDateTime createTime;


}
