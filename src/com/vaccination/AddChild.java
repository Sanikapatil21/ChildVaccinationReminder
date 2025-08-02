package com.vaccination;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectDB;

@WebServlet("/AddChild")
public class AddChild extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddChild() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Read form parameters
		String childName = request.getParameter("childName");
		String parentName = request.getParameter("parentName");
		String dob = request.getParameter("dob");
		String parentContact = request.getParameter("parentContact");
		String address = request.getParameter("address");

		try {
			// Get DB connection
			Connection conn = ConnectDB.dbcon();

			// Prepare SQL insert
			String sql = "INSERT INTO child (childName, parentName, dob, parentContact, address) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, childName);
			ps.setString(2, parentName);
			ps.setString(3, dob);
			ps.setString(4, parentContact);
			ps.setString(5, address);

			// Execute insert
			int rows = ps.executeUpdate();

			if (rows > 0) {
				out.println("<h3>Child added successfully!</h3>");
			} else {
				out.println("<h3>Error: Failed to add child.</h3>");
			}

			// Close resources
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h3>Error: " + e.getMessage() + "</h3>");
		}
	}
}
