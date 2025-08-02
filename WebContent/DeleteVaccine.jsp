<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Vaccine</title>
</head>
<body>
    <h2>Available Vaccines</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Vaccine Name</th>
            <th>Details</th>
            <th>Price</th>
            <th>Months</th>
            <th>Action</th>
        </tr>
        <%
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccineDB", "root", "your_password");
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM vaccine");

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("vaccineName") %></td>
            <td><%= rs.getString("vaccineDetails") %></td>
            <td><%= rs.getDouble("price") %></td>
            <td><%= rs.getInt("months") %></td>
            <td>
                <form action="DeleteVaccine" method="post">
                    <input type="hidden" name="id" value="<%= rs.getInt("id") %>" />
                    <input type="submit" value="Delete" />
                </form>
            </td>
        </tr>
        <%
                }
                rs.close();
                st.close();
                con.close();
            } catch (Exception e) {
                out.println("Error: " + e.getMessage());
            }
        %>
    </table>
</body>
</html>
