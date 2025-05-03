package com.SpringCloudLearning.DeptService.controller;

import com.SpringCloudLearning.DeptService.aop.myLog;
import com.SpringCloudLearning.DeptService.pojo.Dept;
import com.SpringCloudLearning.DeptService.pojo.PageBean;
import com.SpringCloudLearning.DeptService.pojo.Result;
import com.SpringCloudLearning.DeptService.service.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@Slf4j
@RestController
public class DeptController {

    private static Logger log = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;
    //@RequestMapping(value = "/depts", method = RequestMethod.GET) //指定请求方式为GET

    //获取所有部门
    @myLog
    @GetMapping("/depts") //等效
    public Result list(@RequestHeader(value = "user_id", required = false) String user_id,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询员工, 参数: {}, {}, 执行操作用户id: {}", page, pageSize, user_id);
        PageBean deptList = deptService.list(page, pageSize);
        return Result.success(deptList);
    }

    //根据id删除
    @myLog
    @DeleteMapping("/depts/{id}")
    public Result deleteById(@RequestHeader(value = "user_id", required = false) String user_id,
                             @PathVariable Integer id){
        log.info("根据id删除部门:{}, 执行操作用户id: {}", id, user_id);
        deptService.deleteById(id);
        return Result.success(null);
    }

    //插入新部门
    @myLog
    @PostMapping("/depts/add")
    public Result insertDept(@RequestHeader(value = "user_id", required = false) String user_id,
                             @RequestBody Dept dept){
        log.info("新增部门: {}, 执行操作用户id: {}", dept, user_id);
        deptService.insertDept(dept);
        return Result.success(null);
    }
}
