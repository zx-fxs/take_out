package com.sky.controller.User;

import com.sky.dto.OrdersPageQueryDTO;
import com.sky.dto.OrdersSubmitDTO;
import com.sky.mapper.UserOrderDetailMapper;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.UserOrderService;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user/order")
@Api(tags = "订单接口")
public class UserOrderSubmitController {
    @Autowired
    private UserOrderService userOrderService;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation(value = "用户下单")
    public Result<OrderSubmitVO> orderSubmit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("user order :{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = userOrderService.orderSubmit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /**
     * 查询历史订单
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/historyOrders")
    @ApiOperation(value = "查询历史订单")
    public Result<PageResult> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO) {
        log.info("history query :{}", ordersPageQueryDTO);
        PageResult pageResult = userOrderService.pageQuery(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @GetMapping("/orderDetail/{id}")
    @ApiOperation(value = "查询订单详情")
    public Result<OrderVO> detailsQuery(@PathVariable("id") Long id) {
        log.info("query order details :{}", id);
        OrderVO orderVO = userOrderService.detailsQuery(id);
        return Result.success(orderVO);
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @PutMapping("/cancel/{id}")
    @ApiOperation(value = "取消订单")
    public Result orderCancel(@PathVariable("id") Long id) {
        log.info("cancel order :{}", id);
        userOrderService.delete(id);
        return Result.success();
    }
}
