package com.vaccination;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddVaccineLogServlet")
public class AddVaccineLogServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddVaccineLogServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int childID = Integer.parseInt(request.getParameter("childID"));
        String vaccineName = request.getParameter("vaccineName");
        String date = request.getParameter("date");
        int ageMonths = Integer.parseInt(request.getParameter("ageMonths"));

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver"); // For newer MySQL use "com.mysql.cj.jdbc.Driver"
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/childvaccinationdb", "root", "");

            String sql = "INSERT INTO vaccineLogs (childID, vaccineName, date, ageMonths) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, childID);
            pstmt.setString(2, vaccineName);
            pstmt.setString(3, date); // Assuming date is in 'yyyy-MM-dd' format
            pstmt.setInt(4, ageMonths);

            int result = pstmt.executeUpdate();

            if (result > 0) {
                response.sendRedirect("ViewVaccineLogs.jsp");
            } else {
                response.getWriter().println("Failed to insert vaccine log.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
