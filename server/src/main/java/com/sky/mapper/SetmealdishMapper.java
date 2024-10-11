package com.sky.mapper;

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
}
