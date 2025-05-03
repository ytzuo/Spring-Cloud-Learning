package com.SpringCloudLearning.EmpService.controller;

import com.SpringCloudLearning.EmpService.aop.myLog;
import com.SpringCloudLearning.EmpService.pojo.Emp;
import com.SpringCloudLearning.EmpService.pojo.PageBean;
import com.SpringCloudLearning.EmpService.pojo.Result;
import com.SpringCloudLearning.EmpService.service.EmpService;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class EmpController {
    private static Logger log = LoggerFactory.getLogger(EmpController.class);
    @Autowired
    private EmpService empService;

    @myLog
    @DeleteMapping("/emps/{id}")
    public Result delete(@RequestHeader(value = "user_id", required = false) String user_id,
                         @PathVariable Integer id){
        log.info("根据id删除员工: {}, 执行操作用户id: {}", id, user_id);
        empService.deleteById(id);
        return Result.success(null);
    }

    @myLog
    @DeleteMapping("/emps/dept/{id}")
    public Result deleteByDept(@RequestHeader(value = "user_id", required = false) String user_id,
                               @PathVariable Integer dept_id){
        log.info("根据部门删除员工: {}, 执行操作用户id: {}", dept_id, user_id);
        empService.deleteByDept(dept_id);
        return Result.success(null);
    }

    @myLog
    @PostMapping("/emps/add")
    public Result insertEmp(@RequestHeader(value = "user_id", required = false) String user_id,
                            @RequestBody Emp emp){
        log.info("新增员工: {}, 执行操作用户id: {}", emp, user_id);
        empService.insertEmp(emp);
        return Result.success(null);
    }

    @myLog
    @GetMapping("/emps")
    public Result page(@RequestHeader(value = "user_id", required = false) String user_id,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Integer gender){
        log.info("分页查询员工, 参数: {}, {}, {}, {}, 执行操作用户id: {}", page-1, pageSize, name, gender, user_id);
        PageBean pb =  empService.page(page, pageSize, name, gender);
        return Result.success(pb);
    }

    @myLog
    @GetMapping("/emps/{id}")
    public Result getById(@RequestHeader(value = "user_id", required = false) String user_id,
                          @PathVariable Integer id){
        log.info("根据id查询员工信息: id={}, 执行操作用户id: {}", id, user_id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @myLog
    @PostMapping("/emps/update")
    public Result updateById(@RequestHeader(value = "user_id", required = false) String user_id,
                             @RequestBody Emp emp){
        log.info("根据id修改员工信息: {}, 执行操作用户id: {}", emp, user_id);
        empService.updateById(emp);
        return Result.success(null);
    }

}
