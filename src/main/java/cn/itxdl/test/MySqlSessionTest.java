package cn.itxdl.test;

import cn.itxdl.bean.Role;
import cn.itxdl.util.MySqlSession;

import java.util.List;

public class MySqlSessionTest {
    public static void main(String[] args) {
        MySqlSession mySqlSession = new MySqlSession();
        List<Role> users = mySqlSession.query("findRoles");
        for(Role user : users){
            System.out.println(user);
        }
    }
}
