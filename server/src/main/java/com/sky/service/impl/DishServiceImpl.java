package com.sky.service.impl;

import com.sky.dto.DishDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.mapper.DishFlavorsMapper;
import com.sky.mapper.DishMapper;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorsMapper dishFlavorsMapper;

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
}
