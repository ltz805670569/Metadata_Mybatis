package cn.itxdl.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
    private static DataSource dataSource;
    static{
        try {
        FileInputStream fis = new FileInputStream("E:\\JetBrains Workspaces\\IdeaProjects\\CircleLinkedList\\Metadata_Mybatis\\src\\main\\resources\\druid.properties");
        Properties ppt = new Properties();
            ppt.load(fis);
            dataSource = DruidDataSourceFactory.createDataSource(ppt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(ResultSet res, Statement statement,Connection conn){
        try{
            if(res != null){
                res.close();
            }
            if(statement != null){
                statement.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
