package com.cyan.test.hander;

import com.cyan.test.exception.TestException;
import com.cyan.test.util.ResultVOUtil;
import com.cyan.test.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther snow2 Cyan
 * @create 2019/3/11
 */
@ControllerAdvice
public class TestExcepionHandler {
    @ExceptionHandler(value = TestException.class)
    @ResponseBody
    public ResultVO handlerCouponException(TestException e ) {
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
