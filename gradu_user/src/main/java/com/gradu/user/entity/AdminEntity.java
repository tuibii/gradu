package com.gradu.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("admin")
public class AdminEntity implements Serializable {

    private String id;

    private String loginname;

    private String password;

    private String state;

}
