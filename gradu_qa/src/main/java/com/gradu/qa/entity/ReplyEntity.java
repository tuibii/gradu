package com.gradu.qa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("reply")
public class ReplyEntity implements Serializable {

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
    private Date createtime;

    /**
     *  更新时间
     */
    private Date updatetime;

    /**
     *  回答人ID
     */
    private String userid;

    /**
     *  回答人昵称
     */
    private String nickname;
}
