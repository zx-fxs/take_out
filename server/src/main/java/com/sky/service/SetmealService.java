package com.sky.service;


import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;

public interface SetmealService {

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    void insert(SetmealDTO setmealDTO);

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
