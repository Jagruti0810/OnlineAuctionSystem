package com.narola.onlineauctionsystem.dao;

import com.narola.onlineauctionsystem.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {
    public static List<Role> getRoles() {
        List<Role> roles = new ArrayList<>();
        try {
            String sql = "select * from role";
            PreparedStatement ps = DbConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt(1));
                role.setRoleType(rs.getString(2));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }
}