package com.gradu.treehole.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
public class TreeholeEntity implements Serializable {

    @Id
    private String _id;

    private String content;

    private Date publishtime;

    private String userid;

    private String nickname;

    private Integer visits;

    private Integer thumbup;

    private Integer share;

    private Integer comment;

    /**
     *   1 公开  0 私有
     */
    private String state;

    private String parentid;

}
