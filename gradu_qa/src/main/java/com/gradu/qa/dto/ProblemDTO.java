package com.gradu.qa.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProblemDTO{

    private String id;

    private String title;

    private String content;

    private Date createtime;

    private Date updatetime;

    private String userid;

    private String nickname;

    private Integer visits;

    private Integer thumbup;

    private Integer reply;

    private String solve;

    private String replyname;

    private String replytime;

    /**
     *  是否点赞
     */
    private Boolean canThumbup;

    /**
     *  是否关注该问题
     */
    private Boolean focus;
}
