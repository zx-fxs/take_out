package com.sky.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealdishMapper {

    List<Long> getByIds(List<Long> ids);
}
