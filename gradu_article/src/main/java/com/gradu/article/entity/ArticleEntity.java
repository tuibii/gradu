package com.gradu.article.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("article")
public class ArticleEntity implements Serializable {

    /**
     *  ID
     */
    private String id;

    /**
     *  专栏ID
     */
    private String columnid;

    /**
     *  用户ID
     */
    private String userid;

    /**
     *  标题
     */
    private String title;

    /**
     *  正文
     */
    private String content;

    /**
     *  封面
     */
    private String image;

    /**
     *  发表时间
     */
    private Date createtime;

    /**
     *  更新时间
     */
    private Date updatetime;

    /**
     *  是否公开
     */
    private String ispublic;

    /**
     *  是否置顶
     */
    private String istop;

    /**
     *  浏览量
     */
    private Integer visits;

    /**
     *  点赞数
     */
    private Integer thumbup;

    /**
     *  评论数
     */
    private Integer comment;

    /**
     *  审核状态
     */
    private String state;

    /**
     *   频道ID
     */
    private String channelid;

    /**
     *  URL
     */
    private String url;

    /**
     *  类型
     */
    private String type;

}
