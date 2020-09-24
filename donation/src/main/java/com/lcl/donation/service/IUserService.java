package com.lcl.donation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lcl.donation.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lcl.donation.service.vo.request.RequestUserListVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 廖倡霖
 * @since 2020-09-14
 */
public interface IUserService extends IService<User> {
    public User queryByTelephone(String telephone);
    public boolean insertUser(User user);
    public User getUserInfoById(int id);
    public IPage<User> querryPage(RequestUserListVo userListVo);


}
