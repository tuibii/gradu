package com.gradu.qa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReplyDTO implements Serializable {

    /**
     *  主键
     */
    private String id;

    /**
     *  问题ID
     */
    private String problemid;

    /**
     *  回答内容
     */
    private String content;

    /**
     *  创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createtime;

    /**
     *  更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updatetime;

    /**
     *  回答人ID
     */
    private String userid;

    /**
     *  回答人昵称
     */
    private String nickname;
    /**
     *  评分数
     */
    private Double rate;
    /**
     *  评分人数
     */
    private Integer rateCount;
    /**
     * 能否评分
     */
    private Boolean canRate;
}
