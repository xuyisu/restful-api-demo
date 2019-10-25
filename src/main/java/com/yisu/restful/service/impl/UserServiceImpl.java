package com.yisu.restful.service.impl;

import com.yisu.common.result.FwResult;
import com.yisu.restful.data.DataMock;
import com.yisu.restful.entity.User;
import com.yisu.restful.service.UserService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserServiceImpl
 * @Author xuyisu
 * @Description 接口实现
 * @Date 2019/10/25
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public FwResult getUserById(Long id) {
        List<User> collect = DataMock.getUserAll().stream().filter(user -> user.getId() == id).collect(Collectors.toList());
        return FwResult.ok(collect.get(0));
    }

    @Override
    public FwResult deleteUserById(Long id) {
        boolean b = DataMock.getUserAll().removeIf(user -> user.getId() == id);
        if(!b)
            return FwResult.failed("可能没有对应的用户信息");
        return FwResult.ok("删除成功");
    }

    @Override
    public FwResult createUser(User user) {
        user.setId(RandomUtils.nextLong(4,100000));
        DataMock.getUserAll().add(user);
        return FwResult.ok(user);
    }
}
