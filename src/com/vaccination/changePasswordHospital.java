package com.vaccination;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class changePasswordHospital extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver"); // or com.mysql.cj.jdbc.Driver for newer versions
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/childvaccinationdb", "root", "");

            // Validate hospital credentials
            String checkSql = "SELECT * FROM hospital WHERE email = ? AND password = ?";
            stmt = conn.prepareStatement(checkSql);
            stmt.setString(1, email);
            stmt.setString(2, currentPassword);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Update password
                String updateSql = "UPDATE hospital SET password = ? WHERE email = ?";
                stmt = conn.prepareStatement(updateSql);
                stmt.setString(1, newPassword);
                stmt.setString(2, email);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    out.println("<h3>Password updated successfully.</h3>");
                } else {
                    out.println("<h3>Failed to update password.</h3>");
                }
            } else {
                out.println("<h3>Invalid email or current password.</h3>");
            }
        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
}
