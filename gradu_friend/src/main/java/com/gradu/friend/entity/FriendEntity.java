package com.gradu.friend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("friend")
public class FriendEntity  implements Serializable {

    private String userid;

    private String friendid;

    private String islike;
}
