package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
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

    /**
     * 新增套餐
     * @param setmealDishes
     */
    @AutoFill(OperationType.INSERT)
    void insert(List<SetmealDish> setmealDishes);

    /**
     * 起售停售
     * @param id
     * @return
     */
    @AutoFill(OperationType.UPDATE)
    List<Setmeal> saleOrnotQuery(Long id);
}
