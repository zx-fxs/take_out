package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     * @param dish
     */
    @AutoFill(OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 菜品分页查询
     *
     * @param dishPageQuerydto
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQuerydto);

    /**
     * 根据id查询菜品
     * @param ids
     */
    List<Dish> getByIds(List<Long> ids);

    /**
     * 删除菜品
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 修改菜品
     * @param dish
     */
    @AutoFill(OperationType.UPDATE)
    void update(Dish dish);

    /**
     * 根据分类id查询
     * @param categoryId
     * @return
     */
    List<Dish> queryBycategoryId(Long categoryId);

    /**
     * 根据套餐id查询套餐内菜品
     * @param id
     * @return
     */
    List<Dish> getBySetmealId(Long id);
}
