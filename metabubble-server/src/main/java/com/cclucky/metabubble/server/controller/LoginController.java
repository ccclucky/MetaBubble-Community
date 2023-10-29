package com.cclucky.metabubble.server.controller;

import com.cclucky.metabubble.server.common.result.Result;
import com.cclucky.metabubble.server.common.utils.RedisCache;
import com.cclucky.metabubble.server.pojo.dto.LoginDTO;
import com.cclucky.metabubble.server.pojo.entity.LoginUser;
import com.cclucky.metabubble.server.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisCache redisCache;

    @PostMapping("/login")
    @ApiOperation("/用户登录")
    public Result<Map<String, String>> login(@Valid @RequestBody LoginDTO loginDTO){
        return loginService.login(loginDTO);
    }

    @RequestMapping("/user/logout")
    @ApiOperation("/退出登录")
    public Result<Map<String, String>> logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return Result.success("退出成功");
    }
}
