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
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除员工: {}", id);
        empService.deleteById(id);
        return Result.success(null);
    }

    @myLog
    @DeleteMapping("/emps/dept/{id}")
    public Result deleteByDept(@PathVariable Integer dept_id){
        log.info("根据部门删除员工: {}", dept_id);
        empService.deleteByDept(dept_id);
        return Result.success(null);
    }

    @myLog
    @PostMapping("/emps/add")
    public Result insertEmp(@RequestBody Emp emp){
        log.info("新增员工: {}", emp);
        empService.insertEmp(emp);
        return Result.success(null);
    }

    @myLog
    @GetMapping("/emps")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Integer gender){
        log.info("分页查询员工, 参数: {}, {}, {}, {}", page-1, pageSize, name, gender);
        PageBean pb =  empService.page(page, pageSize, name, gender);
        return Result.success(pb);
    }

    @myLog
    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id查询员工信息: id={}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @myLog
    @PostMapping("/emps/update")
    public Result updateById(@RequestBody Emp emp){
        log.info("根据id修改员工信息: {}", emp);
        empService.updateById(emp);
        return Result.success(null);
    }

}
