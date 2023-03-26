package com.example.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.exception.ArgumentException;
import com.example.user.entity.User;
import com.example.user.entity.bo.LoginBO;
import com.example.user.entity.bo.RegisterBO;
import com.example.user.mapper.UserMapper;
import com.example.user.service.IUserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static com.example.user.config.WXApiConstance.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final Gson gson;

    //根据用户名查询
    @Override
    public User queryByName(String name) {
        return userMapper.queryByName(name);
    }

    @Override
    public boolean register(RegisterBO registerBO) {
        User user = new User();
        user.setPhone(registerBO.phone());
        user.setIsAdmin(false);
        user.setIcon(registerBO.icon());
        user.setCreateAt(new Date());
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean login(String code) {
        String res = restTemplate.getForObject(loginUrl + "?appid=" +
                appId + "&secret=" + appSecret + "&js_code=" +
                code + "&grant_type=authorization_code", String.class);
        LoginBO loginBO = gson.fromJson(res, LoginBO.class);
        if (loginBO == null) {
            return false;
        }
        log.debug(loginBO.toString());
        if (loginBO.getErrmsg() != null) {
            throw new ArgumentException(loginBO.getErrmsg());
        }
        User user = userMapper.queryByOpenId(loginBO.getOpenid());
        if (user == null) {
            user = new User();
            user.setOpenId(loginBO.getOpenid());
            user.setSessionKey(loginBO.getSessionkey());
            user.setIsAdmin(false);
            user.setCreateAt(new Date());
            userMapper.insert(user);
        } else {
            user.setSessionKey(loginBO.getSessionkey());
            user.setLastLogin(new Date());
            userMapper.updateById(user);
        }
        return true;
    }

}
