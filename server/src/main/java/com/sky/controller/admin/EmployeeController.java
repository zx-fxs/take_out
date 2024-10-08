package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "员工控制类")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "员工登录")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "员工登出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增用户
     *
     * @param employeedto
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增员工")
    public Result insert(@RequestBody EmployeeDTO employeedto) {
        log.info("新增员工信息：{}", employeedto);
        employeeService.insert(employeedto);
        return Result.success();
    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "员工分页查询")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO) {
        log.info("员工分页查询参数：{}" , employeePageQueryDTO);
        PageResult pageResultResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResultResult);
    }

    /**
     * 员工账号禁用
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation(value = "员工账号禁用")
    public Result forbidden(@PathVariable Integer status, Long id) {
        log.info("员工账号禁用：{}，{}",status,id);
        employeeService.setForbidden(status,id);
        return Result.success();
    }

    /**
     * 根据员工id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据员工id查询")
    public Result getById(@PathVariable Long id) {
        log.info("根据员工id查询：{}",id);
        Employee byId = employeeService.getById(id);
        return Result.success(byId);
    }

    /**
     * 更新员工信息
     * @param employeedto
     * @return
     */
    @PutMapping
    @ApiOperation(value = "更新员工信息")
    public Result update(@RequestBody EmployeeDTO employeedto) {
        log.info("更新员工信息:{}",employeedto);
        employeeService.update(employeedto);
        return Result.success();
    }
}
