package com.yangzl.mall.product.exception;

import com.yangzl.common.enums.ExceptionEnum;
import com.yangzl.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangzl
 * @date 2021/4/7
 * @desc controller 统一异常处理
 *
 *  异常 code 码定义为 5 位数字，前两位表示业务场景，后三位表示错误码
 *      10xxx：通用异常
 *          000：未知异常
 *          001：参数格式校验异常
 *          004：运行时异常
 *          008：DAO 异常
 *      11xxx：商品业务
 *      12xxx：订单业务
 *      13xxx：购物车业务
 *      14xxx：物流业务
 */
@Slf4j
@ResponseBody
@ControllerAdvice("com.yangzl.mall.product.controller")
public class ControllerExceptionAdvice {

    /**
     * 运行时异常 统一处理
     */
    @ExceptionHandler(RuntimeException.class)
    public R handlerRuntimeException(RuntimeException e) {
        log.error("运行异常...... {}, 异常类型：{}", e.getMessage(), e.getClass());

        return R.error(ExceptionEnum.RUNTIME_EXCEPTION.getCode(), ExceptionEnum.RUNTIME_EXCEPTION.getMsg())
            .put(e.getClass().toGenericString(), e.getMessage());
    }

    /**
     * Spring DAO 统一异常处理
     */
    @ExceptionHandler(DataAccessException.class)
    public R handleDAOException(DataAccessException e) {
        log.error("数据访问异常...... {}, 异常类型：{}", e.getMessage(), e.getClass());

        return R.error(ExceptionEnum.DAO_EXCEPTION.getCode(), ExceptionEnum.DAO_EXCEPTION.getMsg())
            .put(e.getClass().toGenericString(), e.getMessage());
    }

    /**
     * 兜底处理所有异常
     */
    @ExceptionHandler(Throwable.class)
    public R handleThrowable(Throwable t) {
        log.error("未知异常...... {}, 异常类型：{}", t.getMessage(), t.getClass());

        return R.error(ExceptionEnum.UNKOWN_EXCEPTION.getCode(), ExceptionEnum.UNKOWN_EXCEPTION.getMsg())
            .put(t.getClass().toGenericString(), t.getMessage());
    }
}
