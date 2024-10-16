package com.sky.controller.User;

import com.sky.dto.OrdersSubmitDTO;
import com.sky.result.Result;
import com.sky.service.UserOrderSubmitService;
import com.sky.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user/order")
@Api(tags = "订单接口")
public class UserOrderSubmitController {
    @Autowired
    private UserOrderSubmitService userOrderSubmitService;

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    @PostMapping("/submit")
    @ApiOperation(value = "用户下单")
    public Result<OrderSubmitVO> orderSubmit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("user order :{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = userOrderSubmitService.orderSubmit(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }
}
