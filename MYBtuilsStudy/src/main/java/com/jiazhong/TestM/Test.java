package com.jiazhong.TestM;

import com.jiazhong.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static java.lang.System.in;

public class Test {
    public static void main(String[] args) {
//        testInsert();
    }

    public static void info() throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/log4j.properties");
        properties.load(fileInputStream);
        PropertyConfigurator.configure(properties);
    }

    @org.junit.jupiter.api.Test
    public void test1() throws IOException{
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/config/log4j.properties");
        properties.load(fileInputStream);
        PropertyConfigurator.configure(properties);


        InputStream in = Resources.getResourceAsStream("config/mybatis-config.xml");
        //从配置文件中获取一个工厂对象
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(in);
        //通过工厂获取sqLsession对象
        SqlSession sqlsession = fac.openSession();
        //sqLsession对象执行映射mapper文件中的sql语句
        List<Student> students = sqlsession.selectList( "EmpMapper.findAll");
        for (Student stu:students)
            System.out.println(stu);
    }

    @org.junit.jupiter.api.Test
    public void testInsert() throws IOException {
        Test.info();
        Student student = new Student();
        student.setsno(108);
        student.setsname("李华");
        student.setssex("女");
        student.setsclass("95211");
        InputStream resourceAsStream = Resources.getResourceAsStream("config/mybatis-config.xml");
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlsession = fac.openSession();
        sqlsession.update("EmpMapper.insertdata",student);
        sqlsession.commit();
    }

    @org.junit.jupiter.api.Test
    public void testUpdatedata() throws IOException {
        Test.info();
        Student student = new Student();
        student.setsno(108);
        student.setsname("lili");
        student.setssex("男");
        student.setsclass("9000");

        InputStream resourceAsStream = Resources.getResourceAsStream("config/mybatis-config.xml");
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlsession = fac.openSession();
        sqlsession.update("EmpMapper.updatedata",student);
        sqlsession.commit();

    }
    @org.junit.jupiter.api.Test
    public void testdelete() throws IOException {
        Test.info();
        InputStream resourceAsStream = Resources.getResourceAsStream("config/mybatis-config.xml");
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlsession = fac.openSession();
        sqlsession.update("EmpMapper.deletedata",108);
        sqlsession.commit();
    }

    @org.junit.jupiter.api.Test
    public void findById() throws IOException {
        Test.info();
        InputStream resourceAsStream = Resources.getResourceAsStream("config/mybatis-config.xml");
        SqlSessionFactory fac = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlsession = fac.openSession();
        List<Student> students = sqlsession.selectList( "EmpMapper.findById",101);
        for (Student stu:students)
            System.out.println(stu);
        sqlsession.commit();

    }
}
