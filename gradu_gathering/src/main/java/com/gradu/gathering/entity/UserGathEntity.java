package com.gradu.gathering.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("usergath")
@Data
public class UserGathEntity implements Serializable {

    private String id;

    private String userid;

    private String gathid;

    private Date exetime;

}
