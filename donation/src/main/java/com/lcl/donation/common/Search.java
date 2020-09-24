package com.lcl.donation.common;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

@Data
public class Search{
    /**
     * 开始查询页数
     */
    private Long displayStart;
    /**
     * 每页展示条数
     */
    private Long displayLength;
}
