package com.gradu.gathering.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("gathering")
@Data
public class GatheringEntity {

    /**
     *  主键
     */
    private String id;

    /**
     *  活动名称
     */
    private String name;

    /**
     *  简介
     */
    private String summary;

    /**
     *  详细说明
     */
    private String detail;

    /**
     *  主办方
     */
    private String sponsor;

    /**
     *  活动图片
     */
    private String image;

    /**
     *  开始时间
     */
    private Date starttime;

    /**
     *  截止时间
     */
    private Date endtime;

    /**
     *  举办地点
     */
    private String address;

    /**
     *  报名截止
     */
    private Date enrolltime;

    /**
     *  是否可见
     */
    private String state;

    /**
     *  城市
     */
    private String city;

}
