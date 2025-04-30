package com.SpringCloudLearning.DeptService.mapper;

import com.SpringCloudLearning.DeptService.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("SELECT * FROM dept")
    List<Dept> list();

    @Delete("DELETE FROM dept WHERE id = #{id}")
    void deleteById(Integer id);

    @Insert("INSERT INTO dept" +
            "(id, name, create_time, update_time) " +
            "VALUES" +
            "(#{id}, #{name}, #{create_time}, #{update_time})")
    void insertDept(Dept dept);
}
