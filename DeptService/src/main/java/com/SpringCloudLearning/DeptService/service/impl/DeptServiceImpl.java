package com.SpringCloudLearning.DeptService.service.impl;

import com.SpringCloudLearning.DeptService.mapper.DeptMapper;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import com.SpringCloudLearning.DeptService.pojo.Dept;
import com.SpringCloudLearning.DeptService.pojo.PageBean;
import com.SpringCloudLearning.DeptService.service.DeptService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Override
    public PageBean list(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Dept> deptList = deptMapper.list();
        Page<Dept> deptPage = (Page<Dept>) deptList;
        return new PageBean(deptPage.getTotal(), deptPage.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer id) {
        Random random = new Random();
        List<ServiceInstance> empService = discoveryClient.getInstances("EmpService");
        if(CollectionUtils.isEmpty(empService)){
            return;
        }
        ServiceInstance si =  empService.get(random.nextInt(empService.size()));
        deptMapper.deleteById(id);
        restTemplate.delete(si.getUri()+"/emps/dept/{id}");
    }

    @Override
    public void insertDept(Dept dept) {
        dept.setCreate_time(Timestamp.valueOf(LocalDateTime.now()));
        dept.setUpdate_time(Timestamp.valueOf(LocalDateTime.now()));
        deptMapper.insertDept(dept);
    }
}
