package com.lcl.donation.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.donation.entity.User;
import com.lcl.donation.mapper.UserMapper;
import com.lcl.donation.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcl.donation.service.vo.request.RequestUserListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;
    private Wrapper<User> userWrapper;

    @Override
    public User queryByTelephone(String telephone){
        Map<String,Object> map = new HashMap<>();
        map.put("telephone",telephone);
        List<User> users = userMapper.selectByMap(map);
        return users.get(0);
    }

    @Override
    public boolean insertUser(User user) {
        int i = userMapper.insert(user);
        System.out.println(i);
        return i!=0;
    }

    @Override
    public User getUserInfoById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public IPage<User> querryPage(RequestUserListVo userListVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //条件构造，当 username，telephone，email不为空时进行模糊查询
        queryWrapper.like(!ObjectUtils.isEmpty(userListVo.getUsername()),"username",userListVo.getUsername());
        queryWrapper.like(!ObjectUtils.isEmpty(userListVo.getTelephone()),"telephone",userListVo.getTelephone());
        queryWrapper.like(!ObjectUtils.isEmpty(userListVo.getEmail()),"email",userListVo.getEmail());
        
        IPage<User> page = new Page<>(userListVo.getDisplayStart(),userListVo.getDisplayLength());
        IPage<User> userPage = userMapper.selectPage(page, queryWrapper);
        return userPage;
    }
}
