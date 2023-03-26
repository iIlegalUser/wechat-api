package com.example.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    //根据用户名查询
    User queryByName(String name);

    //根据openid查询
    User queryByOpenId(String openid);
}
