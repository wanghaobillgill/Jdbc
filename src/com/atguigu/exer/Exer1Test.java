package com.atguigu.exer;

import com.atguigu.util.JDBCUtil;
import com.atguigu.util.SQLUtil;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @Author Hao Wang
 * @Email wanghaobillgill@hotmail.com
 * @Create 2020/3/26 20:29
 * @Project Jdbc
 */
public class Exer1Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name = scanner.next();
        System.out.println("请输入邮箱");
        String email = scanner.next();
        System.out.println("请输入生日");
        String birth = scanner.next();
        String sql="insert intocustomers(name,email,birth) value(?,?,?)";
        int update = SQLUtil.update(sql, name, email, birth);
        if (update>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }
}
