package cn.itxdl.test;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        Connection conn = null;
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String username = "scott";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password);
            DatabaseMetaData metaData = conn.getMetaData();
            int databaseMajorVersion = metaData.getDatabaseMajorVersion();
            int databaseMinorVersion = metaData.getDatabaseMinorVersion();
            String databaseProductName = metaData.getDatabaseProductName();
            String databaseProductVersion = metaData.getDatabaseProductVersion();
            System.out.println(databaseProductName+","+databaseProductVersion);
            System.out.println(databaseMajorVersion+"."+databaseMinorVersion);
            String driverName = metaData.getDriverName();
            String driverVersion = metaData.getDriverVersion();
            System.out.println(driverName+","+driverVersion);
            System.out.println("-----------------------------------------------------------");
            String   catalog          = null;
            String   schemaPattern    = null;
            String   tableNamePattern = null;
            String[] types            = null;
            ResultSet result = metaData.getTables(
                    catalog, schemaPattern, tableNamePattern, types );
            while(result.next()) {
                String tableName = result.getString(3);
                System.out.println(tableName);
            }
            System.out.println("-------------------------------------------------------------");
            String sql1 = "select * from t_user where userid = ? and username = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql1);
            ParameterMetaData metaData1 = preparedStatement.getParameterMetaData();
            int count = metaData1.getParameterCount();
            System.out.println("参数个数："+count);
//            String name = metaData1.getParameterTypeName(1);
//            System.out.println(name);
            System.out.println("-------------------------------------------------------------");
            preparedStatement.setInt(1,3);
            preparedStatement.setString(2,"liubei");
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println("返回结果集列数："+columnCount);
            String columnName = resultSetMetaData.getColumnName(3);
            System.out.println("第三列列名为："+columnName);
            String columnTypeName = resultSetMetaData.getColumnTypeName(3);
            System.out.println("第三列类型为："+columnTypeName);
            String columnLabel = resultSetMetaData.getColumnLabel(4);
            System.out.println(columnLabel);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
