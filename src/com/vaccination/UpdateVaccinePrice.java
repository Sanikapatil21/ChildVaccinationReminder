package com.vaccination;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateVaccinePrice")
public class UpdateVaccinePrice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateVaccinePrice() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("id");
        String priceStr = request.getParameter("price");

        try {
            int id = Integer.parseInt(idStr);
            double price = Double.parseDouble(priceStr);

            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the database
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/childvaccinationdb", "root", ""); // Update DB credentials if needed

            // Prepare SQL query
            String query = "UPDATE vaccine SET price = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setDouble(1, price);
            stmt.setInt(2, id);

            int result = stmt.executeUpdate();

            if (result > 0) {
                out.println("<h3>Vaccine price updated successfully.</h3>");
            } else {
                out.println("<h3>No vaccine found with ID: " + id + "</h3>");
            }

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("UpdateVaccinePrice.html");
    }
}
