package com.merrycodes.bean;

import lombok.Builder;
import lombok.Data;

/**
 * @author MerryCodes
 * @date 2020/4/27 11:31
 */
@Data
public class UserBean {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String sex;

    /**
     * 签名
     */
    private String signature;

}
