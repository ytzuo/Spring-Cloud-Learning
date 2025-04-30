package com.SpringCloudLearning.LoginService.mapper;

import com.SpringCloudLearning.LoginService.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("SELECT * " +
            "FROM emp " +
            "WHERE username = #{username} AND password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
