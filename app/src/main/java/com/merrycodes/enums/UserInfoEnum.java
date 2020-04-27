package com.merrycodes.enums;

import lombok.Getter;

/**
 * @author MerryCodes
 * @date 2020/4/27 12:59
 */
@Getter
public enum UserInfoEnum {

    /**
     * 用户名
     */
    USERNAME("username"),

    /**
     * 昵称
     */
    NICKNAME("nickname"),

    /**
     * 性别
     */
    SEX("sex"),

    /**
     * 签名
     */
    SIGNATURE("signature"),
    ;

    private String value;

    UserInfoEnum(String value) {
        this.value = value;
    }
}
