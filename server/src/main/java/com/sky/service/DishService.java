package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;

public interface DishService {

    /**
     * 新增菜品
     * @param dishdto
     */
    void insert(DishDTO dishdto);

    /**
     * 菜品分页查询
     * @param dishPageQuerydto
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQuerydto);
}
