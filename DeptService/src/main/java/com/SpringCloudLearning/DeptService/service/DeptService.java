package com.SpringCloudLearning.DeptService.service;

import com.SpringCloudLearning.DeptService.pojo.Dept;
import com.SpringCloudLearning.DeptService.pojo.PageBean;

public interface DeptService {
    PageBean list(Integer page, Integer pageSize);

    void deleteById(Integer id);

    void insertDept(Dept dept);
}
