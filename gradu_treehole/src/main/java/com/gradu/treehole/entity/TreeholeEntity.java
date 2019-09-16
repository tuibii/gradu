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

    private Integer visits = 0;

    private Integer thumbup = 0;

    private Integer share = 0;

    private Integer comment = 0;

    private String state;

    private String parentid;

}
