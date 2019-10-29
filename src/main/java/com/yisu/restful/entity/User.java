package com.yisu.restful.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName User
 * @Author xuyisu
 * @Description 用户信息
 * @Date 2019/10/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
