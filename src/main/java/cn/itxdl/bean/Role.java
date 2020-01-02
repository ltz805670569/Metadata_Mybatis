package cn.itxdl.bean;

import java.util.Objects;

public class Role {
    private int id;
    private String rolename;
    private String roleinfo;

    public Role() {
    }

    public Role(int id, String rolename, String roleinfo) {
        this.id = id;
        this.rolename = rolename;
        this.roleinfo = roleinfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoleinfo() {
        return roleinfo;
    }

    public void setRoleinfo(String roleinfo) {
        this.roleinfo = roleinfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(rolename, role.rolename) &&
                Objects.equals(roleinfo, role.roleinfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rolename, roleinfo);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                ", roleinfo='" + roleinfo + '\'' +
                '}';
    }
}
