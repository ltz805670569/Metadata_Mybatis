package cn.itxdl.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MySqlSession {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * 通过参数id，获取对应的sql信息。
     * @param id
     * @param <T>
     * @return
     */
    public <T> List<T> query(String id){
        List<Map<String, MySqlTemplate>> maps = MyXmlResolve.xmlResolve();
        MySqlTemplate mySqlTemplate = null;
        for(Map<String,MySqlTemplate> map : maps){
            mySqlTemplate = map.get(id);
            if(mySqlTemplate != null){
                String resultType = mySqlTemplate.getResultType();
                String sql = mySqlTemplate.getSql();
                try {
                    Class<T> aClass = (Class<T>) Class.forName(resultType);
                    ResultSet resultSet = getResultSet(sql);
                    List<T> list = (List<T>) getData(aClass);
                    return list;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 通过结果集元数据，和反射来构建对象
     * @param aclass
     * @param <T>
     * @return
     */
    public <T> List<T> getData(Class<T> aclass){
        List<T> list = new LinkedList<>();
        try{
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<String> columnList = new ArrayList<>();
            for(int i = 1;i<=columnCount;i++){
                columnList.add(metaData.getColumnName(i));
            }
            Field[] fields = aclass.getDeclaredFields();
            T obj = null;
            while(resultSet.next()){
                obj = aclass.newInstance();
                for(String columnName : columnList){
                    String cName = columnName.toLowerCase();
                    for(Field f:fields){
                        String fName = f.getName();
                        if(fName.equals(cName)){
                            String mName = "set" + fName.substring(0, 1).toUpperCase() + fName.substring(1);
                            String tName = f.getType().getName();
                            Method method = aclass.getMethod(mName, new Class[]{f.getType()});
                            if ("java.lang.Integer".equals(tName) || "int".equals(tName)) {
                                method.invoke(obj, resultSet.getInt(columnName));
                            }
                            else if ("java.lang.Long".equals(tName) || "long".equals(tName)) {
                                method.invoke(obj, resultSet.getLong(columnName));
                            }
                            else if ("java.lang.Float".equals(tName) || "float".equals(tName)) {
                                method.invoke(obj, resultSet.getFloat(columnName));
                            }
                            else if ("java.lang.Double".equals(tName) || "double".equals(tName)) {
                                method.invoke(obj, resultSet.getDouble(columnName));
                            }
                            else if ("java.lang.Boolean".equals(tName) || "boolean".equals(tName)) {
                                method.invoke(obj, resultSet.getBoolean(columnName));
                            }
                            else if ("java.lang.Short".equals(tName) || "short".equals(tName)) {
                                method.invoke(obj, resultSet.getShort(columnName));
                            }
                            else if ("java.lang.String".equals(tName)) {
                                method.invoke(obj, resultSet.getString(columnName));
                            }
                            else if ("java.sql.Date".equals(tName)) {
                                method.invoke(obj, resultSet.getDate(columnName));
                            }
                            else if ("java.sql.Timestamp".equals(tName) || "java.util.Date".equals(tName)) {
                                method.invoke(obj, resultSet.getTimestamp(columnName));
                            }
                            else if ("java.math.BigDecimal".equals(tName)) {
                                method.invoke(obj, resultSet.getBigDecimal(columnName));
                            }
                        }
                    }
                }
                list.add(obj);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        JDBCUtil.close(resultSet,statement,connection);
        return list;
    }

    /**
     * 通过连接池获取结果集
     * @param sql
     * @return
     */
    public  ResultSet getResultSet(String sql){
        connection = JDBCUtil.getConn();
        resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
