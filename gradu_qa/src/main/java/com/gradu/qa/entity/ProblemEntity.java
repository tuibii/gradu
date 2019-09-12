package com.gradu.qa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  问题
 */
@Data
@TableName("problem")
public class ProblemEntity implements Serializable {

    /**
     *  主键
     */
    private String id;

    /**
     *  标题
     */
    private String title;

    /**
     *  内容
     */
    private String content;

    /**
     *  创建时间
     */
    private Date createtime;

    /**
     *  修改时间
     */
    private Date updatetime;

    /**
     *  用户ID
     */
    private String userid;

    /**
     *  昵称
     */
    private String nickname;

    /**
     *  浏览量
     */
    private Integer visits;

    /**
     *  点赞数
     */
    private Integer thumbup;

    /**
     *  回复数
     */
    private Integer reply;

    /**
     *  是否解决
     */
    private String solve;

    /**
     *  回复人昵称
     */
    private String replyname;

    /**
     *  回复时间
     */
    private String replytime;

}
