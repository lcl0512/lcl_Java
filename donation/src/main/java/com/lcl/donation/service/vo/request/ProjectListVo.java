package com.lcl.donation.service.vo.request;

import com.lcl.donation.common.Search;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProjectListVo extends Search {
    private String projectName;
    private int projectStatus;
}
