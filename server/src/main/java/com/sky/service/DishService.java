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

    /**
     * 根据分类id查询
     * @param categoryId
     * @return
     */
    List<DishVO> queryBycategoryId(Long categoryId);

    /**
     * 起售停售
     * @param dishDTO
     */
    void saleOrnot(DishDTO dishDTO);

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    List<DishVO> listWithFlavor(Dish dish);
}
