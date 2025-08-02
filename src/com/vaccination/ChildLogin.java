package com.vaccination;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.connection.ChildPOJO;

@WebServlet("/ChildLogin")
public class ChildLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String childName = request.getParameter("childName");
        String dob = request.getParameter("dob");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Load MySQL driver and connect
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/childvaccinationdb", "root", ""); // Add password if required

            String query = "SELECT * FROM child WHERE childName = ? AND dob = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, childName);
            pst.setString(2, dob);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                // Store login info in POJO
                ChildPOJO.setChildName(childName);
                ChildPOJO.setDob(dob);

                //out.println("<h2>Login successful. Welcome, " + ChildPOJO.getChildName() + "!</h2>");
                // You can also redirect to a child dashboard
                 response.sendRedirect("Child_Dashboard.html");
            } else {
                //out.println("<h2>Invalid credentials. Please try again.</h2>");
                 response.sendRedirect("ChildLogin.html");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
