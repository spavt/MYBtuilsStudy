package com.jiazhong.Student;

import com.jiazhong.pojo.Student;

import java.util.List;

public interface StudentMapper {
     int insertdata1(Student record);
     void updatedata1(Student record);
     void deletedata1(Integer sno);
     List<Student> findAll1();

}
