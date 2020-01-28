package com.gradu.qa.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("pl")
public class ProblemUserEntity implements Serializable {

    private String problemid;

    private String userid;

}
