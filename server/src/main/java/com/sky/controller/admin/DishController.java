package com.sky.controller.admin;


import com.sky.dto.DishDTO;

import com.sky.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/dish")
@Slf4j
@Api(tags = "菜品接口")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishdto
     */
    @PostMapping
    @ApiOperation(value = "新增菜品")
    public void insert(@RequestBody DishDTO dishdto) {
        log.info("insert dish:{}", dishdto);
        //TODO 请求出错了：Cannot set properties of undefined (setting 'type')
        dishService.insert(dishdto);
    }
}
