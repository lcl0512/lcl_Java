package com.lcl.donation.service.vo.request;

import java.time.LocalDateTime;

public interface parentDonationInfo {
     Integer userId = null;

     Integer projectId = null;

     String projectName = null;

     String projectDesc = null;

     Integer type = null;

     String donor = null;

     String status = null;

     LocalDateTime createTime = null;
}
