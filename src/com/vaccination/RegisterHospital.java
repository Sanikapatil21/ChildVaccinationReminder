package com.vaccination;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectDB;

@WebServlet("/register")
public class RegisterHospital extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterHospital() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Hash the password here before storing it (consider using BCrypt)
            // For now, it will just store plain text
            Connection con = ConnectDB.dbcon();
            PreparedStatement ps = con.prepareStatement("INSERT INTO hospital (email, password) VALUES (?, ?)");

            ps.setString(1, email);
            ps.setString(2, password);

            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("HospitlLogin.html"); // Redirect to login page on success
            } else {
                response.sendRedirect("index.html"); // Redirect to index on failure
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
