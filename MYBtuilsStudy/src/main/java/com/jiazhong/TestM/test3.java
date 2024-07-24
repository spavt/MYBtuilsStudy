package com.jiazhong.TestM;

import com.jiazhong.Student.DaoStudent;
import com.jiazhong.Student.StudentMapper;
import com.jiazhong.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class test3 {
    private static InputStream in;//配置文件
    private static SqlSessionFactory fac;//工厂对象
    private static SqlSession session;
    private static StudentMapper daoStudent;


    public static void pre() throws IOException {
        in = Resources.getResourceAsStream("config/mybatis-config.xml");
        fac= new SqlSessionFactoryBuilder().build(in);
        session = fac.openSession();
        daoStudent=session.getMapper(StudentMapper.class);
    }

    public static void main(String[] args) throws IOException {
//        finddata();
//        Insertdata();
//        updatedata();
        deletedata();
    }



    public static  void finddata() throws IOException {
        pre();
        List<Student> all = daoStudent.findAll1();
        for (Student student : all) {
            System.out.println(student);
        }
    }



    public static void Insertdata() throws IOException {
        pre();
        Student student = new Student();
        student.setsno(108);
        student.setsname("张三");
        student.setssex("男");
        student.setsclass("98521");
        daoStudent.insertdata1(student);
        session.commit();
    }

    public static void updatedata() throws IOException {
        pre();
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        System.out.println("请输入要修改的id");
        int id = scanner.nextInt();
        student.setsno(id);
        System.out.println("请输入要修改的姓名");
        String name = scanner.next();
        student.setsname(name);
        System.out.println("请输入要修改的性别");
        String sex = scanner.next();
        student.setssex(sex);
        System.out.println("请输入要修改的班级");
        String sclass = scanner.next();
        student.setsclass(sclass);
        daoStudent.updatedata1(student);
        session.commit();

    }


    public static void deletedata() throws IOException {
        pre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的id");
        int id = scanner.nextInt();
        daoStudent.deletedata1(id);
        session.commit();
    }
}
