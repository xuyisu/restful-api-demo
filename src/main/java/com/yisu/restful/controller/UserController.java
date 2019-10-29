package com.yisu.restful.controller;

import cn.hutool.json.JSONUtil;
import com.yisu.common.result.FwResult;
import com.yisu.common.validate.aop.FwValidate;
import com.yisu.restful.data.DataMock;
import com.yisu.restful.entity.User;
import com.yisu.restful.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;

/**
 * @ClassName UserController
 * @Author xuyisu
 * @Description 用户控制层
 * @Date 2019/10/25
 */
@Controller
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据id查询用户 表达式 \\d+主要是为了限制id为整数
     * @Author xuyisu
     * @Date 2019/10/25
     * @Param [id]
     * @Return com.yisu.common.result.FwResult
     */
    @ApiOperation(value = "根据id查询用户")
    @GetMapping("/{id:\\d+}")
    public FwResult getUser(@PathVariable Long id)
    {
        FwResult userInfo = userService.getUserById(id);
        return userInfo;
    }
    /**
     * 根据id删除用户
     * @Author xuyisu
     * @Date 2019/10/25
     * @Param [id]
     * @Return com.yisu.common.result.FwResult
     */
    @ApiOperation(value = "根据id删除用户")
    @DeleteMapping("/{id:\\d+}")
    public FwResult deleteUser(@PathVariable Long id)
    {
        FwResult userInfo = userService.deleteUserById(id);
        return userInfo;
    }
    /**
     * 添加用户
     * @Author xuyisu
     * @Date 2019/10/25
     * @Param [user]
     * @Return com.yisu.common.result.FwResult
     */
    @PostMapping
    @ApiOperation(value = "创建用户")
    @FwValidate
    public FwResult createUser(@Valid @RequestBody User user,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return FwResult.failed();
        }
        FwResult userInfo=userService.createUser(user);
        return userInfo;
    }

    /**
     * 更新用户
     * @Author xuyisu
     * @Date 2019/10/25
     * @Param [user]
     * @Return com.yisu.common.result.FwResult
     */
    @PutMapping("/{id:\\d+}")
    @ApiOperation(value = "更新用户")
    @FwValidate
    public FwResult updateUser(@PathVariable Long id,@Validated(User.Update.class) @RequestBody User user,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return FwResult.failed();
        }
        FwResult userInfo=userService.updateUser(user);
        return userInfo;
    }

    @PostMapping("/page")
    @ApiOperation(value = "用户查询服务")
    public FwResult query(@RequestBody User user,
                            @PageableDefault(page = 2, size = 17) Pageable pageable) {
        System.out.println(JSONUtil.toJsonStr(user));
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());


        return FwResult.ok(DataMock.getUserAll());
    }


}
