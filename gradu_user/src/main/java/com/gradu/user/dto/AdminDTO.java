package com.gradu.user.dto;

import lombok.Data;

@Data
public class AdminDTO {

    private String id;

    private String loginname;

    private String password;

    private String state;

    private String captcha;

    private String uuid;

}
