package com.yisu.common.validate.aspect;

import com.yisu.common.exception.ValidateException;
import com.yisu.common.result.FwResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.*;

/**
 * @ClassName FwValidateAsprct
 * @Author xuyisu
 * @Description 验证信息切面
 * @Date 2019/10/25
 */
@Aspect
@Component
public class FwValidateAsprct {
    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.yisu.common.validate.aop.FwValidate)")
    public void validatePointcut() {}

    @Around("validatePointcut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if(arg instanceof BindingResult) {
                BindingResult errors = (BindingResult)arg;
                if (errors.hasErrors()) {
                    throw new ValidateException(fail(errors));
                }
            }
        }
        Object result = pjp.proceed();
        return result;
    }


    private Set<Map<String,String>> fail(BindingResult errors){
        Set<Map<String,String>> res=new HashSet<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        fieldErrors.stream().forEach((FieldError fieldError)->{
            Map<String,String> map = new HashMap<>();
            map.put(fieldError.getField(),fieldError.getDefaultMessage());
            res.add(map);
        });
        return res;
    }
}
