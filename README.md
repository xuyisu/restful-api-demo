# restfu-api-demo

#### 介绍
restfu-api-demo基于 Spring Boot 2.1.6 

#注意事项
1.分组校验
实体类定义如下：
```java
public class User implements Serializable  {
    private static final long serialVersionUID = 1838417777538323571L;

    //主键
    @NotNull(message = "主键不能为空",groups =Update.class)
    private Long id;
    //用户名
    @NotNull(message = "用户名不能为空")
    private String userName;
    //密码
    @NotNull(message = "密码不能为空")
    private String password;
    //性别
    @NotNull(message = "性别不能为空")
    private String sex;


    public @interface Update {
    }
}
```
控制层代码,BindingResult bindingResult  必须写在方法的接收参数上，否则校验失败，校验逻辑统一走@FwValidate切面处理
```java
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
    public FwResult updateUser(@Validated(User.Update.class) @RequestBody User user,BindingResult bindingResult) {
        if(bindingResult.hasErrors())
        {
            return FwResult.failed();
        }
        FwResult userInfo=userService.updateUser(user);
        return userInfo;
    }
```



