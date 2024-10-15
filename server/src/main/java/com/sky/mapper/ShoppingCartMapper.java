package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /**
     * 购物车新增商品
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) values " +
            "(#{name},#{image},#{userId},#{dishId},#{setmealId},#{dishFlavor},#{number},#{amount},#{createTime})")
    void add(ShoppingCart shoppingCart);

    /**
     * 查询当前购物车商品
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 根据id修改购物车商品数量
     * @param shoppingCart
     */
    @Update("UPDATE shopping_cart set number = #{number} where id = #{id}")
    void update(ShoppingCart shoppingCart);

    /**
     * 根据用户id清空购物车
     * @return
     */
    @Delete("delete from shopping_cart where user_id =#{l}")
    void delete(long l);

}
