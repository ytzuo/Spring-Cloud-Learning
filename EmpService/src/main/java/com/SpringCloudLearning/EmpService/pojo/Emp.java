package com.SpringCloudLearning.EmpService.pojo;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String img;
    private String job;
    private Integer dept_id;
    private Date entry_time;
    private Timestamp create_time;
    private Timestamp update_time;
}
