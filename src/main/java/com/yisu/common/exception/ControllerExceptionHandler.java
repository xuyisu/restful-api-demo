/**
 * 
 */
package com.yisu.common.exception;

import com.yisu.common.result.FwResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 验证异常处理
 * @Author xuyisu
 * @Date 2019/10/25
 * @Param
 * @Return
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ValidateException.class)
	@ResponseBody
	public FwResult handleValidateException(ValidateException ex) {
		return FwResult.failed(ex.getErrors());
	}

}
