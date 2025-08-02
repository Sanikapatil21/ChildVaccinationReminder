package com.vaccination;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.connection.ConnectDB;
import com.connection.HospitalPOJO;

@WebServlet("/HospitalLogin")  // Correct servlet mapping
public class HospitalLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HospitalLogin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Database connection
            Connection con = ConnectDB.dbcon();
            if (con == null) {
                throw new Exception("Database connection failed!");
            }

            PreparedStatement ps = con.prepareStatement("SELECT * FROM hospital WHERE email=? AND password=?");
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Store email in session (optional)
                request.getSession().setAttribute("hospitalEmail", email);

                // Redirect to welcome page
                response.sendRedirect("Hospital_dashboard.html");
            } else {
                response.sendRedirect("index.html"); // Redirect to home on failed login
            }
        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("error.html"); // Redirect to an error page
        }
    }
}
