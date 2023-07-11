package com.narola.onlineauctionsystem.servlet.user;

import com.narola.onlineauctionsystem.dao.RoleDao;
import com.narola.onlineauctionsystem.model.Role;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleDao roleDao = new RoleDao();
        List<Role> roles = roleDao.getRoles();
        req.setAttribute("roles", roles);
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }
}

