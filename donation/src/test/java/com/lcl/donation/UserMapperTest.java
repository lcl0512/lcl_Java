package com.lcl.donation;

import com.lcl.donation.entity.User;
import com.lcl.donation.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper userMapper;
    @Test
    public void select(){
        Map<String,Object> map = new HashMap<>();
        map.put("telephone","18199987676");
        map.put("password","e10adc3949ba59abbe56e057f20f883e");
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);

    }
}
