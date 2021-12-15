package com.yisu.common.exception;

import com.yisu.common.result.FwResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.security.InvalidParameterException;

/**
 * 验证异常处理
 * @Author xuyisu
 * @Date 2019/10/25
 * @Param
 * @Return
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({InvalidParameterException.class, IllegalArgumentException.class, MissingServletRequestParameterException.class, MethodArgumentNotValidException.class, ConstraintViolationException.class})
	public FwResult paramException(Exception e) {
		if (e instanceof MethodArgumentNotValidException) {
			return FwResult.failed(((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
		}

		if (e.getMessage() != null) {
			return FwResult.failed(e.getMessage());
		}

		return FwResult.failed();
	}
}
