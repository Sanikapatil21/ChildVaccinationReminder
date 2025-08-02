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

@WebServlet("/DeleteVaccine")
public class DeleteVaccine extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteVaccine() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        try {
            int id = Integer.parseInt(idStr);

            // Database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/childvaccinationdb", "root", ""); // Adjust DB name and password

            PreparedStatement ps = con.prepareStatement("DELETE FROM vaccine WHERE id = ?");
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            ps.close();
            con.close();

            if (rowsAffected > 0) {
                response.sendRedirect("ViewVaccine.jsp");
            } else {
                response.getWriter().println("No record found to delete.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error occurred: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
