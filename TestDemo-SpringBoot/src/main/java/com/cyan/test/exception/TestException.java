package com.cyan.test.exception;

import com.cyan.test.enums.ResultEnum;
import lombok.Getter;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@Getter
public class TestException extends RuntimeException{
    private Integer code;

    public TestException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public TestException(Integer code,String message) {
        super(message);
        this.code = code;
    }
}
