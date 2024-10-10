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
    void insert(List<DishFlavor> dishFlavors);

    void delete(List<Long> ids);
}
