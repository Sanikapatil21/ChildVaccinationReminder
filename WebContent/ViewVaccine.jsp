<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Child Vaccination</title>
    <meta charset="UTF-8">
</head>
<body>

    <h2>Vaccine Details</h2>
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Vaccine Name</th>
                <th>Details</th>
                <th>Price</th>
                <th>Months</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                try {
                    // Load MySQL JDBC driver
                    Class.forName("com.mysql.jdbc.Driver");

                    // Connect to the database
                    Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/childvaccinationdb", "root", "");

                    // Prepare and execute SQL query
                    PreparedStatement ps = con.prepareStatement("SELECT * FROM vaccine");
                    ResultSet rs = ps.executeQuery();

                    // Loop through result set
                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getInt("id") %></td>
                <td><%= rs.getString("vaccineName") %></td>
                <td><%= rs.getString("vaccineDetails") %></td>
                <td><%= rs.getDouble("price") %></td>
                <td><%= rs.getInt("months") %></td>
                <td><a href="DeleteVaccine?id=<%= rs.getInt("id") %>">Delete</a></td>
            </tr>
            <%
                    }

                    // Close resources
                    rs.close();
                    ps.close();
                    con.close();
                } catch (Exception e) {
            %>
            <tr>
                <td colspan="6" style="color:red;">Error loading data: <%= e.getMessage() %></td>
            </tr>
            <%
                    e.printStackTrace(); // log to console
                }
            %>
        </tbody>
    </table>

</body>
</html>
