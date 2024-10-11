package com.sky.mapper;

import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealdishMapper {

    /**
     * 根据id查询套餐
     * @param ids
     * @return
     */
    List<Long> getByIds(List<Long> ids);

    /**
     * 根据id查询套餐关联菜品
     * @param id
     * @return
     */
    List<SetmealDish> queryById(Long id);

    void insert(List<SetmealDish> setmealDishes);

    List<Setmeal> saleOrnotQuery(Long id);
}
