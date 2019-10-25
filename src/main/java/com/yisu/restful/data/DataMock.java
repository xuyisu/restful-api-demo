package com.yisu.restful.data;

import com.yisu.restful.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DataMock
 * @Author xuyisu
 * @Description 模拟数据
 * @Date 2019/10/25
 */
public class DataMock {

    public static List<User> getUserAll(){
        List<User> list =new ArrayList<>();
        User user1=new User(1L,"张三","123456","男");
        list.add(user1);
        User user2=new User(2L,"李四","123456","男");
        list.add(user2);
        User user3=new User(3L,"王五","123456","女");
        list.add(user3);
        return list;
    }

}
