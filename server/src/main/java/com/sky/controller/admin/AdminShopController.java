package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/shop")
@Slf4j
@Api(tags = "营业状态")
public class AdminShopController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PutMapping("{status}")
    @ApiOperation(value = "设置营业状态")
    public Result setShopStatus(@PathVariable Integer status) {
        redisTemplate.opsForValue().set("status", status);
        log.info("set status to " + status);
        return Result.success();
    }

    @GetMapping("/status")
    @ApiOperation(value = "获得营业状态")
    public Result<Integer> getShopStatus() {
        Integer status1 = (Integer) redisTemplate.opsForValue().get("status");
        return Result.success(status1);
    }
}
