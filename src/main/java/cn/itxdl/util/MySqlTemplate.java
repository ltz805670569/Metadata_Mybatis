package cn.itxdl.util;

import java.util.Objects;

public class MySqlTemplate {
    private String id;
    private String resultType;
    private String sql;
    public MySqlTemplate() {
    }
    public MySqlTemplate(String resultType, String sql) {
        this.resultType = resultType;
        this.sql = sql;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MySqlTemplate that = (MySqlTemplate) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(resultType, that.resultType) &&
                Objects.equals(sql, that.sql);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, resultType, sql);
    }

    @Override
    public String toString() {
        return "MySqlTemplate{" +
                "resultType='" + resultType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
