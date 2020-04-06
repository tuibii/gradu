package com.gradu.gathering.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DynamicEntity implements Serializable {

    private Long id;

    private String userid;

    private Date createDate;

    private String action;

    private String content;

    private String externalUrl;
}
