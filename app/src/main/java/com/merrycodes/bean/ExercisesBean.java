package com.merrycodes.bean;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author MerryCodes
 * @date 2020/5/12 21:06
 */
@Data
@Builder
public class ExercisesBean {

    /**
     * 每章习题id
     */
    private Integer id;

    /**
     * 每章习题的标题
     */
    private String title;

    /**
     * 每章习题的数目
     */
    private String content;

    /**
     * 每章习题前边的序号背景
     */
    private Integer background;

    /**
     * 每道习题的id
     */
    private Integer subjectId;

    /**
     * 每道习题的题干
     */
    private String subject;

    /**
     * 选项a
     */
    private String a;

    /**
     * 选项b
     */
    private String b;

    /**
     * 选项c
     */
    private String c;

    /**
     * 选项d
     */
    private String d;

    /**
     * 正确答案
     */
    private Integer answer;

    /**
     * 0表示所选的项时正确的，1表示选中的A选项是错误的，以此类推
     */
    private Integer select;


    @Tolerate
    public ExercisesBean() {
    }
}
