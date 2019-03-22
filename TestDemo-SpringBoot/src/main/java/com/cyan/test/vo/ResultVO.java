package com.cyan.test.vo;

import lombok.Data;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Data
public class ResultVO<T> {
    /** 错误码 */
    private Integer code;
    /** 信息 */
    private String msg;
    /** 内容 */
    private T data;
}
