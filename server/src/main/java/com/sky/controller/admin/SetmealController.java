package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增套餐")
    public Result insert(@RequestBody SetmealDTO setmealDTO){
        log.info("insert setmeal:{}",setmealDTO);
        setmealService.insert(setmealDTO);

        return Result.success();
    }

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "套餐分页查询")
    public Result<PageResult> pageQuety(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("pageQuety setmeal:{}",setmealPageQueryDTO);
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改套餐
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("update setmeal:{}",setmealDTO);
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /**
     * 根据id查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查询套餐")
    public Result<SetmealVO> queryById(@PathVariable Long id){
        log.info("queryById setmeal:{}",id);
        SetmealVO setmealVO = setmealService.queryById(id);
        return Result.success(setmealVO);
    }

    /**
     * 起售停售
     * @param status
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "起售停售")
    public Result saleOrnot(@PathVariable Integer status, Long id){
        log.info("saleOrnot setmealId:{}",id);
        SetmealDTO setmealDTO = new SetmealDTO();
        setmealDTO.setId(id);
        setmealDTO.setStatus(status);

        setmealService.update(setmealDTO);
        return Result.success();
    }
}
















