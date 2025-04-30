package com.SpringCloudLearning.EmpService.service.impl;

import com.SpringCloudLearning.EmpService.mapper.EmpMapper;
import com.SpringCloudLearning.EmpService.pojo.Emp;
import com.SpringCloudLearning.EmpService.pojo.PageBean;
import com.SpringCloudLearning.EmpService.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public void deleteById(Integer id) {
        empMapper.deleteById(id);
    }

    @Override
    public void deleteByDept(Integer dept_id) {
        empMapper.deleteByDept(dept_id);
    }

    @Override
    public void insertEmp(Emp emp) {
        emp.setCreate_time(Timestamp.valueOf(LocalDateTime.now()));
        emp.setUpdate_time(Timestamp.valueOf(LocalDateTime.now()));
        empMapper.insertEmp(emp);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Integer gender) {

        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.list(name, gender);
        Page<Emp> empPage = (Page<Emp>) empList;


        return new PageBean(empPage.getTotal(), empPage.getResult());
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void updateById(Emp emp) {
        emp.setCreate_time(Timestamp.valueOf(LocalDateTime.now()));
        emp.setUpdate_time(Timestamp.valueOf(LocalDateTime.now()));
        empMapper.updateById(emp);
    }
}
