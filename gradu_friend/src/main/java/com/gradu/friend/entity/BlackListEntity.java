package com.gradu.friend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("blacklist")
public class BlackListEntity implements Serializable {

    private String userid;

    private String friendid;
}
