package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorsMapper {

    /**
     * 新增口味
     * @param dishFlavors
     */
    @AutoFill(OperationType.INSERT)
    void insert(List<DishFlavor> dishFlavors);

    /**
     * 根据id删除菜品口味
     * @param ids
     * @return
     */
    void delete(List<Long> ids);

    /**
     * 根据id查询菜品口味
     * @param id
     * @return
     */
    List<DishFlavor> getByDishId(Long id);

    /**
     * 修改菜品
     * @param dishFlavor
     */
    @AutoFill(OperationType.UPDATE)
    void update(DishFlavor dishFlavor);
}
