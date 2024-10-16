package com.sky.mapper;

import com.sky.entity.OrderDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserOrderDetailMapper {
    /**
     * 批量插入订单详情
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);

    List<OrderDetail> getByOrderId(Long orderId);

    @Delete("delete from order_detail where order_id =#{id}")
    void delete(Long id);
}
