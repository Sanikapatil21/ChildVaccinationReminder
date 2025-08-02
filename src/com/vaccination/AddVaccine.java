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

@WebServlet("/AddVaccine") // Corrected URL mapping
public class AddVaccine extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AddVaccine() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get form parameters
            String vaccinename = request.getParameter("vaccinename");
            String vaccineDetails = request.getParameter("vaccineDetails");
            String price = request.getParameter("price");
            String months = request.getParameter("months");

            // Get database connection
            Connection con = ConnectDB.dbcon();
            PreparedStatement ps = con.prepareStatement("INSERT INTO vaccine (vaccinename, VaccineDetails, price, months) VALUES (?, ?, ?, ?)");

            // Set parameters
            ps.setString(1, vaccinename);
            ps.setString(2, vaccineDetails);
            ps.setString(3, price);
            ps.setString(4, months);

            // Execute query
            int i = ps.executeUpdate();
            if (i > 0) {
                response.sendRedirect("AddVaccine.html"); // Redirect back to the form
            } else {
                response.sendRedirect("index.html"); // Redirect to homepage if insertion fails
            }
        } catch (Exception e) {
            e.printStackTrace();
            //response.sendRedirect("error.html"); // Redirect to error page in case of an exception
        }
    }
}
