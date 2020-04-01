package com.gradu.gathering.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("gathering")
@Data
public class GatheringEntity implements Serializable {

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date starttime;

    /**
     *  截止时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date endtime;

    /**
     *  举办地点
     */
    private String address;

    /**
     *  报名截止
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date enrolltime;

    /**
     *  是否可见
     */
    private String state;

    /**
     *  城市
     */
    private String city;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createtime;

    private String creator;
}
