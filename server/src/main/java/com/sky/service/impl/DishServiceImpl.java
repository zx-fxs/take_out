package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorsMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealdishMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorsMapper dishFlavorsMapper;

    @Autowired
    private SetmealdishMapper setmealdishMapper;

    /**
     * 新增菜品
     *
     * @param dishdto
     */
    @Transactional
    @Override
    public void insert(DishDTO dishdto) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishdto, dish);
        dishMapper.insert(dish);
        Long id = dish.getId();

        List<DishFlavor> dishFlavors = dishdto.getFlavors();

        if (dishFlavors != null && !dishFlavors.isEmpty()) {
            dishFlavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(id);
            });
            dishFlavorsMapper.insert(dishFlavors);
        }
    }

    /**
     * 菜品分页查询
     * @param dishPageQuerydto
     * @return
     */
    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQuerydto) {
        PageHelper.startPage(dishPageQuerydto.getPage(),dishPageQuerydto.getPageSize());
        Page<Dish> dishes = dishMapper.pageQuery(dishPageQuerydto);
        return new PageResult(dishes.getTotal(),dishes.getResult());
    }

    /**
     * 删除菜品
     * @param ids
     */
    @Transactional
    @Override
    public void delete(List<Long> ids) {
        List<Dish> byIds = dishMapper.getByIds(ids);
        if (byIds != null) {
            for(Dish dish : byIds) {
                if(Objects.equals(dish.getStatus(), StatusConstant.ENABLE)){
                    throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
                }
            }
        }
        List<Long> SetmealbyIds = setmealdishMapper.getByIds(ids);
        if(SetmealbyIds != null && !SetmealbyIds.isEmpty()) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        dishMapper.delete(ids);
        log.info("delete dish success");

        dishFlavorsMapper.delete(ids);
        log.info("delete dishFlavor success");
    }

    /**
     * 根据id查询菜品、口味
     * @param id
     * @return
     */
    @Override
    public DishVO queryById(Long id) {
        List<Dish> dishbyId = dishMapper.getByIds(Collections.singletonList(id));
        Dish dish = dishbyId.get(0);

        List<DishFlavor> dishFlavorbyId = dishFlavorsMapper.getByDishId(id);

        DishVO dishVO = new DishVO();

        BeanUtils.copyProperties(dish, dishVO);
        BeanUtils.copyProperties(dishFlavorbyId, dishVO);

        return dishVO;
    }


}
