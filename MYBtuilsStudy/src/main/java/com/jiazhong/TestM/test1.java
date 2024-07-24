package com.jiazhong.TestM;
import com.jiazhong.Student.DaoStudent;
import com.jiazhong.pojo.Admin;
import com.jiazhong.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class test1 {
    private static InputStream in;//配置文件
    private static SqlSessionFactory fac;//工厂对象
    private static SqlSession session;
    private static DaoStudent daoStudent;


    public static void pre() throws IOException {
        in = Resources.getResourceAsStream("config/mybatis-config.xml");
        fac= new SqlSessionFactoryBuilder().build(in);
        session = fac.openSession();
        daoStudent = session.getMapper(DaoStudent.class);
    }

    public static void main(String[] args) throws IOException {
//        findByIdOne();
//        findByName();
        Insertdata();
    }

    public static  void finddata() throws IOException {
         pre();
        List<Student> all = daoStudent.findAll();
        for (Student student : all) {
            System.out.println(student);
        }
    }

    public static void findByIdOne() throws IOException {
        pre();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查找的id");
        int id = scanner.nextInt();
        Student byId = daoStudent.findById(id);
        System.out.println(byId);
    }

    public static void Insertdata() throws IOException {
        pre();
        Student student = new Student();
        student.setsno(108);
        student.setsname("李华");
        student.setssex("女");
        student.setsclass("95211");
        daoStudent.insertStudent(student);
        session.commit();
        session.close();
    }

    public void Deletedata() throws IOException {
        pre();
        daoStudent.deleteStudent(108);
        session.commit();
        session.close();
    }

    public static void Updatedata() throws IOException {
        pre();
        System.out.println("请输入要修改的学生学号：");
        Scanner scanner = new Scanner(System.in);
        int sno = scanner.nextInt();
        Student student = daoStudent.findById(sno);
        if (student != null) {
            System.out.println("请输入新的学生姓名：");
            student.setsname(scanner.next());
            System.out.println("请输入新的学生性别：");
            student.setssex(scanner.next());
            System.out.println("请输入新的学生班级：");
            student.setsclass(scanner.next());
            int i = daoStudent.updateStudent(student);
            System.out.println(i);
            session.commit();
            session.close();
        } else {
            System.out.println("没有找到该学生！");
        }
    }

    public static void findByName() throws IOException {
        pre();
        System.out.println("请输入要查找的学生姓名：");
        Scanner scanner = new Scanner(System.in);
        String sname = scanner.next();
        List<Student> students = daoStudent.findByName(sname);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void count() throws IOException {
        pre();
        int count = daoStudent.count();
        System.out.println("学生总数为：" + count);
    }

    public static void findAdmin() throws IOException {
        pre();
        System.out.println("请输入用户名：");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        System.out.println("请输入密码：");
        String password = scanner.next();
        Admin admin = daoStudent.findAdmin(username, password);
        if (admin != null) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败！");
        }
    }
}
