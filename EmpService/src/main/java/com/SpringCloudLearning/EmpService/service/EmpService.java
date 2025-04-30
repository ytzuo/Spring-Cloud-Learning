package com.SpringCloudLearning.EmpService.service;

import com.SpringCloudLearning.EmpService.pojo.Emp;
import com.SpringCloudLearning.EmpService.pojo.PageBean;

public interface EmpService {
    //List<Emp> list();

    void deleteById(Integer id);

    void deleteByDept(Integer dept_id);

    void insertEmp(Emp emp);

    PageBean page(Integer page, Integer pageSize, String name, Integer gender);

    Emp getById(Integer id);

    void updateById(Emp emp);
}
