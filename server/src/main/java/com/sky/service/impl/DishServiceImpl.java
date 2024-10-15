package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.entity.Setmeal;
import com.sky.exception.BaseException;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.DishFlavorsMapper;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealdishMapper;
import com.sky.result.PageResult;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        Page<DishVO> dishes = dishMapper.pageQuery(dishPageQuerydto);
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

    /**
     * 修改菜品
     * @param dishDTO
     */
    @Override
    public void update(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);

        DishFlavor dishFlavor = new DishFlavor();
        BeanUtils.copyProperties(dishDTO, dishFlavor);

        dishMapper.update(dish);
        dishFlavorsMapper.update(dishFlavor);
        log.info("update dish success");
    }

    /**
     * 根据分类id查询
     * @param categoryId
     * @return
     */
    @Override
    public List<DishVO> queryBycategoryId(Long categoryId) {
        List<DishVO> dish = dishMapper.queryBycategoryId(categoryId);
        return dish;
    }

    /**
     * 起售停售
     * @param dishDTO
     */
    @Override
    public void saleOrnot(DishDTO dishDTO) {
        List<Setmeal> setmeals = setmealdishMapper.saleOrnotQuery(dishDTO.getId());

        if(setmeals != null && !setmeals.isEmpty() && dishDTO.getStatus().equals(StatusConstant.DISABLE)){
            setmeals.forEach(setmeal -> {
                if(setmeal.getStatus() == StatusConstant.ENABLE){
                    throw new BaseException(MessageConstant.DISH_SET_SALE_BY_MEAL);
                }
            });
        }
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.update(dish);
    }

    /**
     * 条件查询菜品和口味
     * @param dish
     * @return
     */
    @Override
    public List<DishVO> listWithFlavor(Dish dish) {
        List<Dish> dishList = dishMapper.list(dish);

        List<DishVO> dishVOList = new ArrayList<>();

        for (Dish d : dishList) {
            DishVO dishVO = new DishVO();
            BeanUtils.copyProperties(d,dishVO);

            //根据菜品id查询对应的口味
            List<DishFlavor> flavors = dishFlavorsMapper.getByDishId(d.getId());

            dishVO.setFlavors(flavors);
            dishVOList.add(dishVO);
        }

        return dishVOList;
    }


}
