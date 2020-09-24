package com.lcl.donation.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Repository;

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
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    /**
     * 标题
     */
    private String title;

    /**
     * 公告内容
     */
    private String noticeText;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
