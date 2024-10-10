package com.sky.controller.admin;


import com.sky.dto.DishDTO;

import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 菜品分页查询
     * @param dishPageQuerydto
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQuerydto) {
        log.info("page dish:{}", dishPageQuerydto);
        PageResult pageResultResult = dishService.pageQuery(dishPageQuerydto);
        return Result.success(pageResultResult);
    }

    /**
     * 删除菜品
     * @param ids
     */
    @DeleteMapping
    @ApiOperation(value = "删除菜品")
    public void delete(@RequestParam List<Long> ids){
        log.info("delete dish:{}", ids);
        dishService.delete(ids);

    }

    /**
     * 根据id查询菜品
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询菜品")
    public Result<DishVO> QueryById(@PathVariable Long id) {
        log.info("query dish:{}", id);
        DishVO dishVO = dishService.queryById(id);
        return Result.success(dishVO);
    }
}











