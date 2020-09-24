package com.lcl.donation.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.donation.entity.DonationInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcl.donation.entity.ItemList;
import com.lcl.donation.service.vo.request.DonationInfoVo;
import com.lcl.donation.service.vo.response.RespDonationInfoVo;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@Repository
public interface DonationInfoMapper extends BaseMapper<DonationInfo> {
    //多表操作
    @Select({"<script>" +
            "select  donation_info.type,donation_project.project_name,donation_info.create_time,donation_info.status,donation_info.id,donation_info.user_id,project_id,donation_project.project_desc,donation_project.project_status,donation_info.donor,company_info.company_name "+
            "from donation_info,donation_project,company_info where 1=1 "+
            " and donation_info.project_id=donation_project.id "+
            " and donation_project.company_id = company_info.id "+
            "<if test=\" donor !='' \">" +
            //如果查询条件不为空
            " and donation_info.donor LIKE '%${donor}%' " +
            "</if>" +
            "<if test=\" projectName !=''\">" +
            //如果查询条件不为空
            " and donation_info.project_name LIKE '%${projectName}%' " +
            "</if>" +
            "<if test=\" status!='' and status!=null \">" +
            //如果查询条件不为空
            " and donation_info.status = #{status} " +
            "</if>" +
            "<if test=\" type!='' and type!=null \">" +
            //如果查询条件不为空
            " and donation_info.type = #{type} " +
            "</if>" +
            "</script>"})
    @Results(
            id = "DonationInfoVo", value = {
            @Result(column = "id", property = "responseItemListVoList",
                    javaType = java.util.List.class, many = @Many(select = "com.lcl.donation.mapper.DonationInfoMapper.findById")),
            @Result(column = "id",property = "id",id=true),
            @Result(column = "status",property = "status"),
            @Result(column = "type",property = "type")

    })
    List<RespDonationInfoVo> getDonationInfo(DonationInfoVo donationInfoVo);
    @Select("select * from item_list where donation_info_id=#{donationInfoId}")
    List<ItemList> findById(int donationInfoId);
}
