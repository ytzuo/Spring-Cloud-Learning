package com.SpringCloudLearning.LoginService.service.impl;

import com.SpringCloudLearning.LoginService.mapper.LoginMapper;
import com.SpringCloudLearning.LoginService.pojo.Emp;
import com.SpringCloudLearning.LoginService.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Emp login(Emp emp) {
        return loginMapper.getByUsernameAndPassword(emp);
    }
}
