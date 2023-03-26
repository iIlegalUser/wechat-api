package com.example.user.controller;

import com.example.common.exception.ArgumentException;
import com.example.user.entity.User;
import com.example.user.entity.bo.RegisterBO;
import com.example.user.entity.vo.InfoVO;
import com.example.user.service.IUserService;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sail
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    //用户注册
    @RequestMapping("/register")
    public ResponseEntity<String> add(RegisterBO registerBO) {
        if (Strings.isNullOrEmpty(registerBO.phone())) {
            throw new ArgumentException("手机号不能为空");
        }
        if (!userService.register(registerBO)) {
            throw new RuntimeException("用户注册失败");
        }
        return ResponseEntity.ok("success");
    }

    //用户信息
    @GetMapping("/info/{id}")
    public ResponseEntity<InfoVO> info(@PathVariable Integer id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(new InfoVO(user.getPhone(), user.getIcon(), user.getCreateAt()));
    }

    //用户登录
    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestParam String code) {
        if (Strings.isNullOrEmpty(code)) {
            throw new ArgumentException("code不能为空");
        }
        if (userService.login(code)) {
            return ResponseEntity.ok("success");
        } else {
            throw new ArgumentException("用户登录失败");
        }
    }

}
