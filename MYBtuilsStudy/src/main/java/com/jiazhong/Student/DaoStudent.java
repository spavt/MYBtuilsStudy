package com.jiazhong.Student;

import com.jiazhong.pojo.Admin;
import com.jiazhong.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

//使用注解的方式实现，mybatis的接口开发
public interface DaoStudent {
    @Select("select * from students")
    public List<Student> findAll();

    @Select("select * from students where sno = #{sno}")
    public Student findById(Integer sno);

    @Insert("insert into students values(#{sno},#{sname},#{ssex},#{sclass})")
    public void insertStudent(Student student);

    @Delete("delete from students where sno = #{sno}")
    public void deleteStudent(Integer sno);

    @Update("update students set sname = #{sname}, ssex = #{ssex}, sclass = #{sclass} where sno = #{sno}")
    public int updateStudent(Student student);

    @Select("select * from students where sname like '%${value}%'")
    public List<Student> findByName(String sname);

    @Select("select count(*) from students")
    public int count();

    @Select("select * from admin where username =#{username} and password =#{password}")
    public Admin findAdmin( @Param("username") String username, @Param("password") String password);
}
