package com.sky.controller.User;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/shop")
@Slf4j
@Api(tags = "营业状态")
public class UserShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/status")
    @ApiOperation(value = "获得营业状态")
    public Result<Integer> getShopStatus() {
        Integer status1 = (Integer) redisTemplate.opsForValue().get("status");
        return Result.success(status1);
    }
}
