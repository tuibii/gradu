package com.gradu.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginSuccessDTO implements Serializable {
    private String token;

    private String nickname;

    private String avatar;

    public LoginSuccessDTO(String token, String nickname, String avatar) {
        this.token = token;
        this.nickname = nickname;
        this.avatar = avatar;
    }
}
