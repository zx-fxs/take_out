package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

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

    /**
     * 删除菜品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据id查询菜品、口味
     * @param id
     * @return
     */
    DishVO queryById(Long id);

    /**
     * 修改菜品
     * @param dishDTO
     */
    void update(DishDTO dishDTO);

    List<Dish> queryBycategoryId(Long categoryId);

    void saleOrnot(DishDTO dishDTO);
}
