package com.yisu.common.exception;

import com.yisu.common.result.FwResult;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName ValidateException
 * @Author xuyisu
 * @Description 验证的异常捕捉
 * @Date 2019/10/25
 */
@Data
@AllArgsConstructor
public class ValidateException extends  RuntimeException {

    private static final long serialVersionUID = 7207451175263593487L;

    private Set<Map<String,String>> errors;


}
