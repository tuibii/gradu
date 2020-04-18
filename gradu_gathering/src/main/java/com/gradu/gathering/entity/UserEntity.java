package com.gradu.gathering.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserEntity implements Serializable {

    private String id;

    private String mobile;

    private String password;

    private String nickname;

    private String sex;

    private Date birthday;

    private String avatar;

    private String email;

    private Date regdate;

    private Date updatedate;

    private Date lastdate;

    private Long online;

    private String interest;

    private String personality;

    private Integer fanscount;

    private Integer followcount;

}
