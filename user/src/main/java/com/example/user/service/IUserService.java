package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.entity.User;
import com.example.user.entity.bo.RegisterBO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
public interface IUserService extends IService<User> {
    //根据用户名查询
    User queryByName(String name);

    //注册
    boolean register(RegisterBO registerBO);

    //登录
    boolean login(String code);
}
