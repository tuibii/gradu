package com.gradu.base.dto;

import lombok.Data;

@Data
public class LabelDTO {

    /**
     *  id 主键
     */
    private String id;

    /**
     * 标签名称
     */
    private String labelname;

    /**
     * 状态
     */
    private String state;

    /**
     * 使用数量
     */
    private Long count;

    /**
     * 关注数
     */
    private Long fans;

    /**
     * 是否推荐
     */
    private String recommend;
}
