package com.merrycodes.enums;

import lombok.Getter;

/**
 * @author MerryCodes
 * @date 2020/6/22 23:11
 */
@Getter
public enum VideoPlayListEnum {

    /**
     * 用户名
     */
    USERNAME("username"),

    /**
     * 章节id
     */
    CHAPTER_ID("chapterId"),

    /**
     * 视频
     */
    VIDEO_ID("videoId"),

    /**
     * 视频路径
     */
    VIDEO_PATH("videoPath"),

    /**
     * 标题
     */
    TITLE("title"),

    /**
     * 视频标题
     */
    SECONDE_TITLE("secondeTitle"),

    ;


    private String value;

    VideoPlayListEnum(String value) {
        this.value = value;
    }
}
