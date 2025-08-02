<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Child Records</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 30px auto;
        }
        th, td {
            border: 1px solid #999;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: lightgray;
        }
        form {
            display: inline;
        }
    </style>
</head>
<body>
    <h2 align="center">Child Records</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Child Name</th>
            <th>Parent Name</th>
            <th>Date of Birth</th>
            <th>Parent Contact</th>
            <th>Address</th>
            <th>Action</th>
        </tr>

        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/childvaccinationdb", "root", "");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM child");

                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("childName") %></td>
            <td><%= rs.getString("parentName") %></td>
            <td><%= rs.getString("dob") %></td>
            <td><%= rs.getString("parentContact") %></td>
            <td><%= rs.getString("address") %></td>
            <td>
                <form action="DeleteChild" method="post">
                    <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                    <input type="submit" value="Delete" onclick="return confirm('Are you sure to delete this record?');">
                </form>
            </td>
        </tr>
        <%
                }
            } catch(Exception e) {
                out.println("Error: " + e);
            } finally {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
        %>
    </table>
</body>
</html>
