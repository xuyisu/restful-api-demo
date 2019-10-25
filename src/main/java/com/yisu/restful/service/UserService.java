package com.yisu.restful.service;

import com.yisu.common.result.FwResult;
import com.yisu.restful.entity.User;

/**
 * @ClassName service
 * @Author xuyisu
 * @Description 接口信息
 * @Date 2019/10/25
 */
public interface UserService {
    /**
     * 根据id查询用户信息
     * @Author xuyisu
     * @Date 2019/10/25
     * @Param [id]
     * @Return com.yisu.common.result.FwResult
     */
    FwResult getUserById(Long id);
    /**
     *
     * @Author xuyisu
     * @Date 2019/10/25
     * @Param [id]
     * @Return com.yisu.common.result.FwResult
     */
    FwResult deleteUserById(Long id);
    /**
     * 创建用户
     * @Author xuyisu
     * @Date 2019/10/25
     * @Param [user]
     * @Return com.yisu.common.result.FwResult
     */
    FwResult createUser(User user);
}
